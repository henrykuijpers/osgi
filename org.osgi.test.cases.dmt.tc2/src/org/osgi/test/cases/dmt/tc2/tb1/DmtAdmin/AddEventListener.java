/*
 * Copyright (c) OSGi Alliance (2004, 2020). All Rights Reserved.
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
 * 
 */

/*
 * REVISION HISTORY:
 *
 * Date          Author(s)
 * CR            Headline
 * ============  ==============================================================
 * Jul 12, 2005  Luiz Felipe Guimaraes
 * 118           Implement MEG TCK
 * ============  ==============================================================
 */

package org.osgi.test.cases.dmt.tc2.tb1.DmtAdmin;

import org.osgi.service.dmt.security.DmtPermission;
import org.osgi.service.dmt.security.DmtPrincipalPermission;
import org.osgi.service.permissionadmin.PermissionInfo;
import org.osgi.test.cases.dmt.tc2.tbc.DmtConstants;
import org.osgi.test.cases.dmt.tc2.tbc.DmtTestControl;
import org.osgi.test.cases.dmt.tc2.tbc.TestInterface;
import org.osgi.test.support.compatibility.DefaultTestBundleControl;

/**
 * @author Luiz Felipe Guimaraes
 * This test case validates the implementation of <code>addEventListener</code> method of DmtAdmin, 
 * according to MEG specification
 * 
 */

public class AddEventListener implements TestInterface {
	private DmtTestControl tbc;
	
	public AddEventListener(DmtTestControl tbc) {
		this.tbc = tbc;
	}

	@Override
	public void run() {
        prepare();
        testAddEventListener001();
        testAddEventListener002();
        testAddEventListener003();
        testAddEventListener004();
        testAddEventListener005();
        testAddEventListener006();
        testAddEventListener007();
        testAddEventListener008();
        testAddEventListener009();
	}
	
    private void prepare() {
        tbc.setPermissions(
                new PermissionInfo[] {
                new PermissionInfo(DmtPermission.class.getName(), DmtConstants.ALL_NODES,DmtConstants.ALL_ACTIONS),
                new PermissionInfo(DmtPrincipalPermission.class.getName(), DmtConstants.PRINCIPAL, "*") }
                );
    }
	/**
	 * Asserts that IllegalArgumentException is thrown if the type parameter contains 
	 * invalid bits (not corresponding to any event type defined in DmtEvent) 
	 * 
	 * @spec DmtAdmin.addEventListener(int,String,DmtEventListener) 

	 */
	private void testAddEventListener001() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener001");
			// Does not compile anymore, do we need this one anymore?
			// tbc.getDmtAdmin().addEventListener(INVALID_CODE, TestExecPluginActivator.ROOT,event);
			DefaultTestBundleControl.failException("", IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {
			DefaultTestBundleControl.pass("IllegalArgumentException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(IllegalArgumentException.class, e);
		}
	}
	
	/**
	 * Asserts that IllegalArgumentException is thrown if the type parameter contains 
	 * invalid bits (not corresponding to any event type defined in DmtEvent) 
	 * 
	 * @spec DmtAdmin.addEventListener(String,int,String,DmtEventListener) 

	 */
	private void testAddEventListener002() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener002");
			// Does not compile anymore
			// tbc.getDmtAdmin().addEventListener(DmtConstants.PRINCIPAL,INVALID_CODE, TestExecPluginActivator.ROOT, event);
			DefaultTestBundleControl.failException("", IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {
			DefaultTestBundleControl.pass("IllegalArgumentException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(IllegalArgumentException.class, e);
		}
	}
	
	/**
	 * Asserts that NullPointerException is thrown if the uri parameter is null  
	 * 
	 * @spec DmtAdmin.addEventListener(int,String,DmtEventListener) 

	 */
	private void testAddEventListener003() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener003");
			// Does not compile anymore
			// tbc.getDmtAdmin().addEventListener(DmtEvent.ADDED, null,event);
			DefaultTestBundleControl.failException("", NullPointerException.class);
		} catch (NullPointerException e) {
			DefaultTestBundleControl.pass("NullPointerException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(NullPointerException.class, e);
		}
	}
	
	/**
	 * Asserts that NullPointerException is thrown if the events parameter is null  
	 * 
	 * @spec DmtAdmin.addEventListener(int,String,DmtEventListener) 

	 */
	private void testAddEventListener004() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener004");
			// Does not compile anymore
			// tbc.getDmtAdmin().addEventListener(DmtEvent.ADDED, TestExecPluginActivator.ROOT,null);
			DefaultTestBundleControl.failException("", NullPointerException.class);
		} catch (NullPointerException e) {
			DefaultTestBundleControl.pass("NullPointerException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(NullPointerException.class, e);
		}
	}
	
	/**
	 * Asserts that NullPointerException is thrown if the principal parameter is null  
	 * 
	 * @spec DmtAdmin.addEventListener(String,int,String,DmtEventListener) 

	 */
	private void testAddEventListener005() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener005");
			// Does not compile anymore
			// tbc.getDmtAdmin().addEventListener(null,DmtEvent.ADDED, TestExecPluginActivator.ROOT,event);
			DefaultTestBundleControl.failException("", NullPointerException.class);
		} catch (NullPointerException e) {
			DefaultTestBundleControl.pass("NullPointerException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(NullPointerException.class, e);
		}
	}
	
	/**
	 * Asserts that NullPointerException is thrown if the uri parameter is null  
	 * 
	 * @spec DmtAdmin.addEventListener(String,int,String,DmtEventListener) 

	 */
	private void testAddEventListener006() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener006");
			// Does not compile anymore
			//tbc.getDmtAdmin().addEventListener(DmtConstants.PRINCIPAL,DmtEvent.ADDED, null,event);
			DefaultTestBundleControl.failException("", NullPointerException.class);
		} catch (NullPointerException e) {
			DefaultTestBundleControl.pass("NullPointerException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(NullPointerException.class, e);
		}
	}
	
	/**
	 * Asserts that NullPointerException is thrown if the events parameter is null  
	 * 
	 * @spec DmtAdmin.addEventListener(String,int,String,DmtEventListener) 

	 */
	private void testAddEventListener007() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener007");
			// Does not compile anymore
			// tbc.getDmtAdmin().addEventListener(DmtConstants.PRINCIPAL,DmtEvent.ADDED, TestExecPluginActivator.ROOT,null);
			DefaultTestBundleControl.failException("", NullPointerException.class);
		} catch (NullPointerException e) {
			DefaultTestBundleControl.pass("NullPointerException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(NullPointerException.class, e);
		}
	}
	
	/**
	 * Asserts that SecurityException is thrown if the caller doesn't 
	 * have the necessary GET DmtPermission for the given URI 
	 * 
	 * @spec DmtAdmin.addEventListener(int,String,DmtEventListener) 

	 */
	private void testAddEventListener008() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener008");
	        //DmtPermission is not present, so SecurityException must be thrown 
	        tbc.setPermissions(new PermissionInfo[0]);
			// Does not compile anymore
			// tbc.getDmtAdmin().addEventListener(DmtEvent.ADDED, TestExecPluginActivator.ROOT,event);
			DefaultTestBundleControl.failException("", SecurityException.class);
		} catch (SecurityException e) {
			DefaultTestBundleControl.pass("SecurityException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(SecurityException.class, e);
			prepare();
		}
	}
	
	/**
	 * Asserts that SecurityException is thrown if the caller doesn't have the necessary 
	 * DmtPrincipalPermission to use the specified principal 
	 * 
	 * @spec DmtAdmin.addEventListener(String,int,String,DmtEventListener) 

	 */
	private void testAddEventListener009() {
		try {
			DefaultTestBundleControl.log("#testAddEventListener009");
			tbc.setPermissions(new PermissionInfo(DmtPrincipalPermission.class.getName(), 
					DmtConstants.PRINCIPAL_2, "*"));
			// Does not compile anymore
			//tbc.getDmtAdmin().addEventListener(DmtConstants.PRINCIPAL,DmtEvent.ADDED, TestExecPluginActivator.ROOT,event);
			DefaultTestBundleControl.failException("", SecurityException.class);
		} catch (SecurityException e) {
			DefaultTestBundleControl.pass("SecurityException correctly thrown");
		} catch (Exception e) {
			tbc.failExpectedOtherException(SecurityException.class, e);
			prepare();
		}
	}

}

