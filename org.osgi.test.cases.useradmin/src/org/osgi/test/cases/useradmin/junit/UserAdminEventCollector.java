/**
 * OSGi Test Suite Implementation. OSGi Confidential.
 */
package org.osgi.test.cases.useradmin.junit;

import java.util.Comparator;

import org.osgi.framework.ServiceReference;
import org.osgi.service.useradmin.Role;
import org.osgi.service.useradmin.UserAdminEvent;
import org.osgi.service.useradmin.UserAdminListener;
import org.osgi.test.support.EventCollector;

public class UserAdminEventCollector extends EventCollector<UserAdminEvent>
		implements
		UserAdminListener {
	private final int	mask;

	public UserAdminEventCollector(int typeMask) {
		this.mask = typeMask;
	}

	public void roleChanged(UserAdminEvent event) {
		if ((event.getType() & mask) != 0)
			addEvent(event);
	}

	public Comparator<UserAdminEvent> getComparator() {
		return new Comparator<UserAdminEvent>() {
			public int compare(UserAdminEvent event1, UserAdminEvent event2) {

				ServiceReference< ? > ref1 = event1.getServiceReference();
				ServiceReference< ? > ref2 = event2.getServiceReference();
				int result = ref1.compareTo(ref2);
				if (result != 0) {
					return result;
				}
				Role role1 = event1.getRole();
				Role role2 = event2.getRole();

				result = role1.getName().compareTo(role2.getName());
				if (result != 0) {
					return result;
				}
				result = role1.getType() - role2.getType();
				if (result != 0) {
					return result;
				}

				result = event1.getType() - event2.getType();
				return result;
			}
		};
	}
}
