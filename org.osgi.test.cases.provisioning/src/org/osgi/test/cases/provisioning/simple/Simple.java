/*
 * Copyright (c) OSGi Alliance (2000, 2020). All Rights Reserved.
 *
 * Implementation of certain elements of the OSGi
 * Specification may be subject to third party intellectual property
 * rights, including without limitation, patent rights (such a third party may
 * or may not be a member of the OSGi Alliance). The OSGi Alliance is not responsible and shall not be
 * held responsible in any manner for identifying or failing to identify any or
 * all such third party intellectual property rights.
 *
 * This document and the information contained herein are provided on an "AS
 * IS" basis and THE OSGI ALLIANCE DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO ANY WARRANTY THAT THE USE OF THE INFORMATION HEREIN WILL
 * NOT INFRINGE ANY RIGHTS AND ANY IMPLIED WARRANTIES OF MERCHANTABILITY OR
 * FITNESS FOR A PARTICULAR PURPOSE. IN NO EVENT WILL THE OSGI ALLIANCE BE LIABLE FOR ANY
 * LOSS OF PROFITS, LOSS OF BUSINESS, LOSS OF USE OF DATA, INTERRUPTION OF
 * BUSINESS, OR FOR DIRECT, INDIRECT, SPECIAL OR EXEMPLARY, INCIDENTIAL,
 * PUNITIVE OR CONSEQUENTIAL DAMAGES OF ANY KIND IN CONNECTION WITH THIS
 * DOCUMENT OR THE INFORMATION CONTAINED HEREIN, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH LOSS OR DAMAGE.
 *
 * All Company, brand and product names may be trademarks that are the sole
 * property of their respective owners. All rights reserved.
 */

package org.osgi.test.cases.provisioning.simple;
import java.security.AllPermission;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.provisioning.ProvisioningService;

public class Simple implements BundleActivator {
	
	public void start(BundleContext context) {
		Dictionary<String,Object> properties = new Hashtable<>();
		properties.put("test.case", "org.osgi.test.cases.provisioning" );
		if ( context.getBundle().hasPermission( new AllPermission() ) )
			properties.put( "allpermission", "true" );
		else
			properties.put( "allpermission", "false" );

		ServiceReference<ProvisioningService> ref = context
				.getServiceReference(ProvisioningService.class);
		ProvisioningService		ps 			= context.getService( ref );
		byte [] data = (byte[]) ps.getInformation().get( ProvisioningService.PROVISIONING_AGENT_CONFIG );
		if ( data != null )
			properties.put( "config.data", data );
		
		context.registerService(Bundle.class, context.getBundle(), properties);
	}
	public void stop( BundleContext context ) {
		// empty
	}
}
