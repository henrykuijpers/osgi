/*
 * Copyright (c) OSGi Alliance (2004, 2011). All Rights Reserved.
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
 * Date          Author(s)
 * CR            Headline
 * ============  ==============================================================
 * Abr 04, 2005  Luiz Felipe Guimaraes
 * 34            [MEGTCK][DMT] CVS update 
 * ============  ==============================================================
 */

package org.osgi.test.cases.dmt.tc1.tbc.DmtPermission;

import org.osgi.test.cases.dmt.tc1.tbc.DmtTestControl;

/**
 * @generalDescription This class tests all of <code>constants<code> of DmtPermission
 */

public class DmtPermissionConstants extends DmtTestControl {
	/**
	 * This method asserts the constants values
	 */
	public void testConstants001() {
		log("#testConstants001");
		assertEquals("Asserting DmtPermission.ADD", "Add",
				org.osgi.service.dmt.security.DmtPermission.ADD);
		assertEquals("Asserting DmtPermission.DELETE", "Delete",
				org.osgi.service.dmt.security.DmtPermission.DELETE);
		assertEquals("Asserting DmtPermission.EXEC", "Exec",
				org.osgi.service.dmt.security.DmtPermission.EXEC);
		assertEquals("Asserting DmtPermission.GET", "Get",
				org.osgi.service.dmt.security.DmtPermission.GET);
		assertEquals("Asserting DmtPermission.REPLACE", "Replace",
				org.osgi.service.dmt.security.DmtPermission.REPLACE);
	}
	

}
