/*
 * Copyright (c) OSGi Alliance (2013, 2015). All Rights Reserved.
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

package org.osgi.impl.service.rest.resources;

import org.osgi.framework.ServiceReference;
import org.osgi.impl.service.rest.PojoReflector;
import org.osgi.impl.service.rest.RestService;
import org.osgi.impl.service.rest.pojos.ServiceRepresentationList;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;

/**
 * The service representations resource, a list of the full representations of
 * all services.
 * 
 * @author Jan S. Rellermeyer, IBM Research
 */
public class ServiceRepresentationsResource extends
		AbstractOSGiResource<ServiceRepresentationList> {

	private static final MediaType	MEDIA_TYPE	= MediaType.valueOf("application/org.osgi.services.representations");

	public ServiceRepresentationsResource() {
		super(PojoReflector.getReflector(ServiceRepresentationList.class), MEDIA_TYPE);
	}

	@Override
	public Representation get(final Variant variant) {
		try {
			final String filter = getQuery().getFirstValue(RestService.FILTER_ID_KEY);
			final ServiceReference<?>[] srefs = getBundleContext()
					.getAllServiceReferences(null, filter);
			return getRepresentation(new ServiceRepresentationList(srefs), variant);
		} catch (final Exception e) {
			return ERROR(e, variant);
		}
	}

}
