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
 */

/*
 * REVISION HISTORY:
 *
 * Date         Author(s)
 * CR           Headline
 * ===========  ==============================================================
 * 21/JAN/2005  Andre Assad
 * CR 1         Implement MEG TCK
 * ===========  ==============================================================
 * Feb 11, 2005 Alexandre Santos
 * 1            Updates after formal inspection (BTC_MEG_TCK_CODE-INSPR-001)
 * ===========  ==============================================================
 */

package org.osgi.test.cases.dmt.tc2.tb1.DmtSession;

import org.osgi.service.dmt.DmtSession;
import org.osgi.service.dmt.security.DmtPermission;

import org.osgi.service.permissionadmin.PermissionInfo;
import org.osgi.test.cases.dmt.tc2.tbc.DmtTestControl;
import org.osgi.test.cases.dmt.tc2.tbc.TestInterface;
import org.osgi.test.support.compatibility.DefaultTestBundleControl;

import junit.framework.TestCase;

/**
 * @author Andre Assad
 * 
 * This test case validates the implementation of <code>getSessionId</code> method of DmtSession, 
 * according to MEG specification
 */
public class GetSessionId implements TestInterface {
	private DmtTestControl tbc;

	public GetSessionId(DmtTestControl tbc) {
		this.tbc = tbc;
	}

	@Override
	public void run() {
        prepare();
		testGetSessionId001();
	}
    private void prepare() {
        tbc.setPermissions(new PermissionInfo[] {
                new PermissionInfo(DmtPermission.class.getName(), ".", DmtPermission.GET)});
    }
	/**
	 * This method asserts that two different sessions have differents ids
	 * 
	 * @spec DmtSession.getSessionId()
	 */
	private void testGetSessionId001() {
		DmtSession session1 = null;
		DmtSession session2 = null;
		try {
			DefaultTestBundleControl.log("#testGetSessionId001");
			session1 = tbc.getDmtAdmin().getSession(".",
					DmtSession.LOCK_TYPE_SHARED);
			session2 = tbc.getDmtAdmin().getSession(".",
					DmtSession.LOCK_TYPE_SHARED);
			TestCase.assertTrue(
					"Asserting that session1 and session2 has differents ids.",
					(session1.getSessionId() != session2.getSessionId()));
		} catch (Exception e) {
			tbc.failUnexpectedException(e);
		} finally {
			tbc.closeSession(session1);
            tbc.closeSession(session2);
		}

	}
}
