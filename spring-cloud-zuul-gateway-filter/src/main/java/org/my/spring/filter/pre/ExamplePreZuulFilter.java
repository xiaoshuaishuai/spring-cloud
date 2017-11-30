package org.my.spring.filter.pre;

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
public class ExamplePreZuulFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(ExamplePreZuulFilter.class);

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
	 * "pre" for pre-routing filtering,【请求之前做些数据校验、包装工作】
	 * "route" for routing to an origin,【请求转发阶段，请求具体实例之前和之后执行该过滤器】
	 * "post" for post-routing filters,【响应之后的过滤器，包装响应结果，也可以拿到请求信息】
	 * "error" for error handling.【发生异常触发该过滤器，但最终还是流向post过滤器】
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
