/**
 * OSGi Test Suite Implementation. OSGi Confidential.
 * (C) Copyright Telia Research AB. 2000.
 * This source code is owned by Telia Research AB,
 * and is being distributed to OSGi MEMBERS as
 * MEMBER LICENSED MATERIALS under the terms of section 3.2 of
 * the OSGi MEMBER AGREEMENT.
 */
package org.osgi.test.cases.http.tb1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

/*
 * Testbundle 2 for test case 5
 */
public class HttpTestBundle2 implements BundleActivator {
	HttpService	http;

	public void start(BundleContext context) throws Exception {
		ServiceReference<HttpService> ref = context
				.getServiceReference(HttpService.class);
		if (ref == null)
			throw new RuntimeException(
					"No Http Service available in http.tbc.tb1");
		http = context.getService(ref);
		if (http == null)
			throw new RuntimeException(
					"No Http Service available in http.tbc.tb1");
		http.registerServlet("/tc5servlet", new HttpTestServlet1(), null, null);
	}

	public void stop(BundleContext bc) throws Exception {
	}
}
