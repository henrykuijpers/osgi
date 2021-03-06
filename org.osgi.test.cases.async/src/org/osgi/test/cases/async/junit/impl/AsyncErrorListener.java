/*
 * Copyright (c) OSGi Alliance (2014). All Rights Reserved.
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
package org.osgi.test.cases.async.junit.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.osgi.framework.ServiceException;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.util.promise.Deferred;
import org.osgi.util.promise.Promise;

public class AsyncErrorListener implements LogListener {
	Collection<Deferred<LogEntry>> promises = new ArrayList<Deferred<LogEntry>>();
	public void logged(LogEntry entry) {
		if (entry.getException() instanceof ServiceException) {
			// Only looking for entries with ServiceException of type ASYNC_ERROR
			ServiceException error = (ServiceException) entry.getException();
			if (error.getType() != ServiceException.ASYNC_ERROR) {
				return;
			}
		} else if (!(entry.getException() instanceof MyServiceException)) {
			// ignore all other exceptions
			return;
		}
		Collection<Deferred<LogEntry>> copy;
		synchronized (promises) {
			copy = new ArrayList<Deferred<LogEntry>>(promises);
			promises.clear();
		}
		for (Deferred<LogEntry> deferred : copy) {
			deferred.resolve(entry);
		}
	}
	
	public Promise<LogEntry> getAsyncError() {
		Deferred<LogEntry> deferred = new Deferred<LogEntry>();
		synchronized (promises) {
			promises.add(deferred);
		}
		return deferred.getPromise();
	}
}
