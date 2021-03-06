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

package org.osgi.test.cases.permissionadmin.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.service.permissionadmin.PermissionInfo;

/**
 * Used to exit from the permissions calling context.
 * 
 * @author Petia Sotirova
 * @version 1.0
 */
public interface PermissionSignatureTBCService {

	// from Bundle class
	public Dictionary<String,String> callBundle_getHeaders(Bundle bundle);

	public Dictionary<String,String> callBundle_getHeaders(Bundle bundle,
			String localeString);

	public String callBundle_getLocation(Bundle bundle);

	public URL callBundle_getResource(Bundle bundle, String name);

	public Enumeration<URL> callBundle_getResources(Bundle bundle, String name)
			throws IOException;

	public URL callBundle_getEntry(Bundle bundle, String name);

	public Enumeration<String> callBundle_getEntryPaths(Bundle bundle,
			String path);

	public Class< ? > callBundle_loadClass(Bundle bundle, String name)
			throws ClassNotFoundException;

	public void callBundle_stop(Bundle bundle) throws BundleException;

	public void callBundle_uninstall(Bundle bundle) throws BundleException;

	public void callBundle_update(Bundle bundle) throws BundleException;

	public void callBundle_update(Bundle bundle, InputStream in)
			throws BundleException;

	public void callBundle_start(Bundle bundle) throws BundleException;

	// from BundleContext class
	public Bundle callBundleContext_installBundle(BundleContext context,
			String location) throws BundleException;

	public Bundle callBundleContext_installBundle(BundleContext context,
			String location, InputStream input) throws BundleException;

	public void callBundleContext_addBundleListener(BundleContext context);

	public void callBundleContext_removeBundleListener(BundleContext context);

	// from StartLevel service
	public void callStartLevel_setBundleStartLevel(Bundle bundle,
			Integer startlevel);

	public void callStartLevel_setStartLevel(Integer startlevel);

	public void callStartLevel_setInitialBundleStartLevel(Integer startlevel);

	// from PermisssionAdmin service
	public void callPermissionAdmin_setPermissions(String location,
			PermissionInfo[] permissions);

	public void callPermissionAdmin_setDefaultPermissions(
			PermissionInfo[] permissions);

	// from PackageAdmin service
	public void callPackageAdmin_refreshPackages(Bundle[] bundles);

	public boolean callPackageAdmin_resolveBundles(Bundle[] bundles);

	// from FrameworkWiring
	public void callFrameworkWiring_refreshBundles(Bundle... bundles);
	
	public boolean callFrameworkWiring_resolveBundles(Bundle... bundles);
	
	// from BundleStartLevel
	public void callBundleStartLevel_setStartLevel(Bundle bundle,
			Integer startlevel);

	// from FrameworkStartLevel
	public void callFrameworkStartLevel_setStartLevel(Integer startlevel);

	public void callFrameworkStartLevel_setInitialBundleStartLevel(
			Integer startlevel);
}
