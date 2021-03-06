/*
 * Copyright (c) OSGi Alliance (2012). All Rights Reserved.
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
package org.osgi.impl.bundle.component.annotations;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Component;

/**
 *
 *
 */
@Component(xmlns = "http://www.osgi.org/xmlns/scr/v1.0.0")
public class HelloWorld10 {
	/**
	 */
	protected void activate(ComponentContext context) {
		System.out.println("Hello World!");
	}

	/**
	 */
	protected void deactivate(ComponentContext context) {
		System.out.println("Goodbye World!");
	}
}
