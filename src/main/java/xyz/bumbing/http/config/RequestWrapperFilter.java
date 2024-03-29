package xyz.bumbing.http.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/*")
public class RequestWrapperFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {
		// Do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		RequestWrapper wrapper = new RequestWrapper((HttpServletRequest) request);
		chain.doFilter(wrapper, response);
	}

	@Override
	public void destroy() {
		// Do nothing
	}
}