package org.my.spring.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * zuul api url 请求过滤
 * 
 * @author ShuaishuaiXiao
 */
public class ExampleZuulFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(ExampleZuulFilter.class);

	@Override
	public boolean shouldFilter() {
		String path = RequestContext.getCurrentContext().getRequest().getRequestURI();
		logger.info("getRequestURI:" + path);
		return true;
	}

	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

		logger.info("request method {} request to {}", request.getMethod(), request.getRequestURL().toString());

		String token = request.getParameter("token");
		if (token == null) {
			logger.info("access fail !"); 
			RequestContext.getCurrentContext().setSendZuulResponse(false);
			RequestContext.getCurrentContext().setResponseStatusCode(401);
			RequestContext.getCurrentContext().setResponseBody("access fail !");
			return null;
		}
		logger.info("access ok !"); 
		return null;
	}

	/**
	 * 过滤时机类型
	 * 
	 * "pre" for pre-routing filtering, "route" for routing to an origin, "post"
	 * for post-routing filters, "error" for error handling.
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
