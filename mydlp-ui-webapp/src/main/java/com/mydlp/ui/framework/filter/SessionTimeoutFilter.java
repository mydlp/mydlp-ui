package com.mydlp.ui.framework.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import flex.messaging.FlexContext;
import flex.messaging.FlexSession;

@Service("sessionTimeoutFilter")
public class SessionTimeoutFilter implements javax.servlet.Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest) req).getSession(false);
			if (session == null)
			{
				FlexSession flexSession = FlexContext.getFlexSession();
				if (flexSession != null)
					flexSession.invalidate();
				SecurityContextHolder.clearContext();
			}
			else
			{
				session.setMaxInactiveInterval(15*60);
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
