/*
 * Copyright (c) OSGi Alliance (2004, 2016). All Rights Reserved.
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

package org.osgi.test.cases.component.tb2.impl;

import java.util.Dictionary;

import org.osgi.service.component.ComponentContext;
import org.osgi.test.cases.component.service.ServiceProvider;
import org.osgi.test.cases.component.service.TestObject;
import org.osgi.test.cases.component.tb2.ServiceConsumerLookup;

/**
 * @author $Id$
 */
public class ServiceConsumerLookupImpl implements ServiceConsumerLookup {

	private ComponentContext	context;

	public ServiceConsumerLookupImpl() {
		// empty
	}

	protected void activate(ComponentContext c) {
		this.context = c;
	}

	protected void deactivate(ComponentContext c) {
		this.context = null;
	}

	public Dictionary<String,Object> getProperties() {
		return context.getProperties();
	}

	public TestObject getTestObject() {
		ServiceProvider serviceProvider = (ServiceProvider) context
				.locateService("serviceProvider");
		return serviceProvider.getTestObject();
	}

	public void enableComponent(String name, boolean flag) {
		if (flag) {
			context.enableComponent(name);
		}
		else {
			context.disableComponent(name);
		}
	}
}
