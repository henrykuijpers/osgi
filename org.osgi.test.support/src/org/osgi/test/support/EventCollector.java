/*
 * Copyright (c) OSGi Alliance (2007, 2012). All Rights Reserved.
 * 
 * Implementation of certain elements of the OSGi Specification may be subject
 * to third party intellectual property rights, including without limitation,
 * patent rights (such a third party may or may not be a member of the OSGi
 * Alliance). The OSGi Alliance is not responsible and shall not be held
 * responsible in any manner for identifying or failing to identify any or all
 * such third party intellectual property rights.
 * 
 * This document and the information contained herein are provided on an "AS IS"
 * basis and THE OSGI ALLIANCE DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO ANY WARRANTY THAT THE USE OF THE INFORMATION
 * HEREIN WILL NOT INFRINGE ANY RIGHTS AND ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. IN NO EVENT WILL THE
 * OSGI ALLIANCE BE LIABLE FOR ANY LOSS OF PROFITS, LOSS OF BUSINESS, LOSS OF
 * USE OF DATA, INTERRUPTION OF BUSINESS, OR FOR DIRECT, INDIRECT, SPECIAL OR
 * EXEMPLARY, INCIDENTIAL, PUNITIVE OR CONSEQUENTIAL DAMAGES OF ANY KIND IN
 * CONNECTION WITH THIS DOCUMENT OR THE INFORMATION CONTAINED HEREIN, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH LOSS OR DAMAGE.
 * 
 * All Company, brand and product names may be trademarks that are the sole
 * property of their respective owners. All rights reserved.
 */
package org.osgi.test.support;

import static org.osgi.test.support.OSGiTestCase.fail;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class EventCollector<T> {
	private final List<T>	events	= new ArrayList<T>();

	synchronized protected void addEvent(T event) {
		events.add(event);
		notifyAll();
	}
	
	synchronized public void clear() {
		events.clear();
	}

	synchronized public List<T> getList(int expectedCount, long waitTime) {
		final long endTime = System.currentTimeMillis() + waitTime;
		while (events.size() < expectedCount) {
			if (waitTime <= 0) {
				break; // timeout has elapsed
			}
			try {
				wait(waitTime);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				fail("Unexpected interruption.", e);
			}
			waitTime = endTime - System.currentTimeMillis();
		}
		List<T> result = new ArrayList<T>(events);
		clear();
		return result;
	}

	public List<T> getListSorted(int expectedCount, long waitTime) {
		List<T> result = getList(expectedCount, waitTime);
		Collections.sort(result, getComparator());
		return result;
	}

	public abstract Comparator<T> getComparator();
}
