/*
 * Copyright (c) OSGi Alliance (2010). All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.osgi.impl.service.coordinator;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.osgi.framework.ServiceException;
import org.osgi.service.coordinator.Coordination;
import org.osgi.service.coordinator.CoordinationException;
import org.osgi.service.coordinator.CoordinationPermission;
import org.osgi.service.coordinator.Participant;
import org.osgi.service.log.LogService;

public class CoordinationImpl implements Coordination {
	private final static Timer		timer			= new Timer();
	private static final AtomicInteger	counter			= new AtomicInteger(
																1000);
	private final long					id;
	private final List<Participant>		participants	= new CopyOnWriteArrayList<Participant>();
	private CoordinatorImpl				coordinator;
	private final String				name;
	private final Map<Class< ? >, Object>	variables		= new ConcurrentHashMap<Class< ? >, Object>();

	class FailTimer extends TimerTask {
		public void run() {
			fail(Coordination.TIMEOUT);
		}
	}

	private TimerTask	timeouter	= new FailTimer();
	private volatile long		deadline	= 0;
	private volatile Throwable	failure;
	volatile boolean			terminated	= false;
	volatile Thread				stackThread;

	CoordinationImpl(CoordinatorImpl coordinator, String name, int timeout) {
		this.coordinator = coordinator;
		this.name = name;
		this.id = counter.incrementAndGet();

		if (timeout > 0) {
			deadline = System.currentTimeMillis() + timeout;
			timer.schedule(timeouter, new Date(deadline));
		}
	}

	public void end() throws CoordinationException {
		check(CoordinationPermission.INITIATE);

		if (wasTerminated(null))
			alreadyEnded();

		boolean partiallyFailed = false;

		for (Participant p : participants) {
			try {
				p.ended(this);
			}
			catch (Throwable t) {
				partiallyFailed = true;
				coordinator.log.log(LogService.LOG_ERROR, "Participant " + p
						+ " throws exception in ended(" + name + ")", t);
			}
			finally {
				unlock(p);
			}
		}

		unlockAll();
		coordinator = null;

		synchronized (this) {
			notifyAll();
		}
		if (partiallyFailed)
			throw new CoordinationException("Participants failed, see log",
					this, CoordinationException.PARTIALLY_ENDED);
	}

	public long extendTimeout(int timeInMillis) {
		check(CoordinationPermission.INITIATE);

		if (deadline == 0 || timeInMillis == 0)
			return deadline;

		timeouter.cancel();
		deadline = deadline + timeInMillis;
		synchronized (this) {
			if (!terminated) {
				timeouter.cancel();
				timeouter = new FailTimer();
				timer.schedule(timeouter, new Date(deadline));
			}
		}
		return deadline;
	}

	private synchronized boolean wasTerminated(Throwable f) {
		if (terminated)
			return true;

		this.failure = f;
		timeouter.cancel();
		coordinator.clear(this);
		terminated = true;

		return false;
	}

	public boolean fail(Throwable f) {
		Thread thread = stackThread;
		if (wasTerminated(f))
			return false;

		for (Participant p : participants) {
			try {
				p.failed(this);
			}
			catch (Throwable t) {
				coordinator.log.log(LogService.LOG_ERROR, "Participant " + p
						+ " throws exception in failed(" + this + ")", t);
			}
			finally {
				unlock(p);
			}
		}

		unlockAll();
		coordinator = null;

		synchronized (this) {
			notifyAll();
		}
		if (thread != null && thread != Thread.currentThread())
			thread.interrupt();

		return true;
	}

	public Throwable getFailure() {
		return failure;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List< ? extends Participant> getParticipants() {
		check(CoordinationPermission.INITIATE);
		return new ArrayList<Participant>(participants);
	}

	public Map<Class< ? >, Object> getVariables() {
//		check(CoordinationPermission.PARTICIPATE);
		return variables;
	}

	public void addParticipant(Participant p) {
		check(CoordinationPermission.PARTICIPATE);
		if (lock(p))
			participants.add(p); // Only adds once
	}

	public synchronized boolean isTerminated() {
		return terminated;
	}

	public String toString() {
		return name + ":" + id;
	}

	public synchronized void join(long timeoutInMillis)
			throws InterruptedException {
		check(CoordinationPermission.PARTICIPATE);
		while (!terminated)
			wait(timeoutInMillis);
	}

	public Thread getThread() {
		check(CoordinationPermission.ADMIN);
		return stackThread;
	}

	private boolean lock(Participant p) {
		if (coordinator == null)
			alreadyEnded();

		synchronized (coordinator.locks) {
			while (true) {
				if (isTerminated())
					alreadyEnded();

				CoordinationImpl other = coordinator.locks.get(p);
				if (other == null)
					break;

				if (other == this)
					return false;

				if (other.stackThread == Thread.currentThread())
					throw new CoordinationException(
							"Trying to use the same participant on multiple coordinations in the same thread",
							this, CoordinationException.DEADLOCK_DETECTED);

				try {
					coordinator.locks.wait(coordinator.timeout);
				}
				catch (InterruptedException e) {
					throw new CoordinationException("Interrupted", this,
							CoordinationException.LOCK_INTERRUPTED);
				}
			}
			coordinator.locks.put(p, this);
			return true;
		}
	}

	private void alreadyEnded() {
		if (failure != null)
			throw new CoordinationException("Already ended", this,
					CoordinationException.FAILED, failure);
		else
			throw new CoordinationException("Already ended", this,
					CoordinationException.ALREADY_ENDED, failure);
	}

	private void unlock(Participant p) {
		synchronized (coordinator.locks) {
			CoordinationImpl c = coordinator.locks.remove(p);
			assert c == this;
			coordinator.locks.notifyAll();
		}
	}

	private void unlockAll() {
		synchronized (coordinator.locks) {
			Iterator<Map.Entry<Participant, CoordinationImpl>> i = coordinator.locks
					.entrySet().iterator();
			while (i.hasNext()) {
				Entry<Participant, CoordinationImpl> next = i.next();
				if (next.getValue() == this)
					i.remove();
			}
			coordinator.locks.notifyAll();
		}
	}

	private void check(String admin) {
		synchronized (this) {
			if (terminated)
				alreadyEnded();
		}

		CoordinatorImpl c = coordinator;
		if (c == null)
			throw new ServiceException("No longer active service",
					ServiceException.UNREGISTERED);

		if (c.sm == null)
			return;

		CoordinationPermission cp = new CoordinationPermission(c.bundle, name,
				admin);
		c.sm.checkPermission(cp);
	}

}