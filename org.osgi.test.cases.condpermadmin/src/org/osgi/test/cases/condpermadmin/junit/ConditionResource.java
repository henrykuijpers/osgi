
package org.osgi.test.cases.condpermadmin.junit;

import java.util.ResourceBundle;

public class ConditionResource {

	private static final String			BUNDLE_NAME		= "org.osgi.test.cases.condpermadmin.junit.condition";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private ConditionResource() {
	}

	public static String getString(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}
}
