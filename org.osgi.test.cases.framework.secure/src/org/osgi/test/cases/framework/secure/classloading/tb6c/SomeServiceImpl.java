/*
 * Copyright (c) OSGi Alliance (2005, 2016). All Rights Reserved.
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

package org.osgi.test.cases.framework.secure.classloading.tb6c;

import java.security.AccessController;

import org.osgi.framework.Bundle;
import org.osgi.test.cases.framework.secure.classloading.exports.security.SomePermission;
import org.osgi.test.cases.framework.secure.classloading.exports.service.SomeService;

/**
 * A simple service implementation used in tests
 * 
 * @author left
 * @author $Id$
 */
public class SomeServiceImpl implements SomeService {

	@SuppressWarnings("unused")
	private Bundle	registeringBundle;

	/**
	 * Creates a new instance of SomeServiceImpl
	 */
	public SomeServiceImpl(Bundle _registeringBundle) {
		registeringBundle = _registeringBundle;
	}

	/**
	 * Return the registrant bundle
	 * 
	 * @return the registrant bundle
	 */
	public Bundle getRegistrantBundle() {
		SomePermission permission;

		permission = new SomePermission("", "");
		AccessController.checkPermission(permission);

		throw new IllegalStateException(
				"The permission class is loaded from the incorrect bundle");
	}

}
