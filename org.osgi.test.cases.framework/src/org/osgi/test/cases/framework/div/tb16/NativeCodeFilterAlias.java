/*
 * Copyright (c) OSGi Alliance (2005, 2010). All Rights Reserved.
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

package org.osgi.test.cases.framework.div.tb16;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.test.cases.div.tb2.NativeCode;

/**
 * Bundle for the NativeCode selection filter test. This bundle has no optional
 * clause present so the bundle should NOT be loaded if no other native code
 * clause matches. The clauses were built to match in order to
 * check if the bundle is properly loaded for the specified windowing systems.
 * 
 * @author Jorge Mascena
 */
public class NativeCodeFilterAlias implements BundleActivator {
	/**
	 * Starts the bundle. Excercises the native code. The bundle should not be
	 * loaded since no native code clause matches and no optional clause is
	 * present.
	 *  
	 * @param bc the context where the bundle is executed.
	 */
	public void start(BundleContext bc) throws BundleException {
		NativeCode.test();
	}

	/**
	 * Stops the bundle.
	 * 
	 * @param bc the context where the bundle is executed.
	 */
	public void stop(BundleContext bc) {
		// empty
	}
}
