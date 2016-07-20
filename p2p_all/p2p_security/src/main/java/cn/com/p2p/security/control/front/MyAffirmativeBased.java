package cn.com.p2p.security.control.front;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

public class MyAffirmativeBased extends AffirmativeBased {

	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes) {

		super.decide(authentication, object, configAttributes);
		if (configAttributes != null) {
			for (ConfigAttribute tmp : configAttributes)

				if (tmp != null) {
					if (tmp.toString().indexOf("isAuthenticated()") != -1) {

						((FilterInvocation) object).getResponse().setHeader(
								"Cache-Control", "no-cache");
						((FilterInvocation) object).getResponse().setHeader(
								"Cache-Control", "no-store");
						((FilterInvocation) object).getResponse()
								.setDateHeader("Expires", 0);
						((FilterInvocation) object).getResponse().setHeader(
								"Pragma", "no-cache");

						break;
					}
				}

		}
	}
}
