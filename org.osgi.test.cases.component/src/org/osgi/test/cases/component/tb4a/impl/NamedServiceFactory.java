/*
 * Copyright (c) OSGi Alliance (2004, 2013). All Rights Reserved.
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
package org.osgi.test.cases.component.tb4a.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.log.LogService;
import org.osgi.test.cases.component.tb4a.NamedService;

/**
 */
public class NamedServiceFactory implements NamedService {

	@interface Config {
		String name();
	}

	private BundleContext	context;
	@SuppressWarnings("unused")
	private ComponentContext				cc;
	@SuppressWarnings("unused")
	private Map<String,Object>				props;
	private Config							config;

	private final Collection<LogService>	logs;

	public NamedServiceFactory(ComponentContext componentContext,
			BundleContext bundleContext, Map<String,Object> properties,
			Config someProperties,
			Collection<LogService> logs) {
		if (componentContext != null && bundleContext != null
				&& properties != null && someProperties != null
				&& componentContext.getBundleContext().equals(bundleContext)
				&& Objects.equals(componentContext.getProperties().get("name"),
						someProperties.name())) {
			this.logs = logs;
		} else {
			this.logs = Collections.emptyList();
		}
	}

	@Override
	public String getName() {
		return config.name();
	}

	@Override
	public String toString() {
		return getName() + " " + !getLogServices().isEmpty();
	}

	@Override
	public BundleContext getBundleContext() {
		return context;
	}

	private Collection<LogService> getLogServices() {
		return logs;
	}

}
