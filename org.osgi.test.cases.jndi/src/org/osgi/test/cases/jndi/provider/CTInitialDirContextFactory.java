/*
 * Copyright (c) IBM Corporation (2010). All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.osgi.test.cases.jndi.provider;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

/**
 * @author $Id$
 */
public class CTInitialDirContextFactory implements InitialContextFactory {

	private Hashtable<String,Object> env;
	
	public CTInitialDirContextFactory(Hashtable<String,Object> env) {
		this.env = env;
	}
	
	public CTInitialDirContextFactory() {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Context getInitialContext(Hashtable< ? , ? > environment)
			throws NamingException {
		Map<String,Object> envMap = new HashMap<>();
		if (environment != null) {
			envMap.putAll(
					(Map< ? extends String, ? extends Object>) environment);
		}
		
		if (env != null) {
			envMap.putAll(env);
		}
		
		CTDirContext context = new CTDirContext(envMap);
		return context;
	}

}
