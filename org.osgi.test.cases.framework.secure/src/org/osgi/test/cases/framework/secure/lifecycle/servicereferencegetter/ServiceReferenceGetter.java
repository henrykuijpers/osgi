/** 
 * OSGi Test Suite Implementation. OSGi Confidential.
 * (C) Copyright Ericsson Telecom AB. 2001.
 * This source code is owned by Ericsson Telecom AB, and is being distributed to OSGi 
 * MEMBERS as MEMBER LICENSED MATERIALS under the terms of section 3.2 of the OSGi MEMBER AGREEMENT. 
 */
package org.osgi.test.cases.framework.secure.lifecycle.servicereferencegetter;

import org.osgi.framework.ServiceReference;

/**
 *  
 */
public interface ServiceReferenceGetter {
	/**
	 *  
	 */
	public void setServiceReference(ServiceReference< ? > serviceReference);
}
