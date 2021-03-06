package org.osgi.test.cases.device.tbc.locators;

import static org.osgi.test.support.compatibility.DefaultTestBundleControl.log;

import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Dictionary;

import org.osgi.service.device.DriverLocator;
import org.osgi.test.cases.device.tbc.TestBundleControl;

/**
 * The locator used in the device detection test. For all IDs it returns an
 * InputStream to the basic driver (in fact this is the only driver bundle in
 * the whole test case)
 */
public class BasicTestLocator implements DriverLocator {
	final TestBundleControl	master;

	/**
	 * @param master the master of test case - used for logging
	 */
	public BasicTestLocator(TestBundleControl master) {
		this.master = master;
	}

	/**
	 * Searches for drivers
	 * 
	 * @param props the properties of the registered Device service
	 * @return a single element String array containing the property with key
	 *         "deviceID" from the props Dictionary
	 */
	public String[] findDrivers(Dictionary<String, ? > props) {
		log("searching for " + props.get("deviceID"));
		String[] toReturn = new String[1];
		toReturn[0] = (String) props.get("deviceID");
		return toReturn;
	}

	/**
	 * Finds an InputStream to a driver with the passed id. This implementation
	 * returns always InputStream to the same bundle.
	 * 
	 * @param id the id of the driver to be loaded
	 * @return an InputStream to the basicdrv.jar bundle
	 */
	public InputStream loadDriver(String id) {
		log("loading for " + id);
		try {
			return AccessController
					.doPrivileged(new PrivilegedExceptionAction<InputStream>() {
						public InputStream run() throws Exception {
							URL url = new URL(master.getWebServer()
									+ "drv1.jar");
							return url.openStream();
						}
					});
		}
		catch (PrivilegedActionException e) {
			e.getException().printStackTrace();
			log("error while reading the /drivers/basicdrv.jar resource");
			return null;
		}
	}

}
