package org.my.spring.filter.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


/**
 * 处理post异常过滤器  ，配合Errorfilter及SendErrorFilter
 * 
 * 在Errorfilter 之后执行。优先级比Errorfilter低，然后交由SendErrorFilter run() 处理响应
 * 
 * 
 * @author ShuaishuaiXiao
 */
@Component
public class PostErrorExampleZuulFilter extends SendErrorFilter{
	private static final Logger logger = LoggerFactory.getLogger(PostErrorExampleZuulFilter.class);

	@Override
	public boolean shouldFilter() {//只拦截post过滤器类型，需要自定义FilterProcessor包装ZuulFilter获取到过滤器类型
		RequestContext context = RequestContext.getCurrentContext();
		ZuulFilter filter  = (ZuulFilter) context.get("filter.cust.error");
		if(null!=filter&&filter.filterType().equals("post")) {
			return true;
		}
		return false;
	}

//	@Override
//	public Object run() {
//		logger.info("1000 error filter=================");
//	
//	
//		return null;
//	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 1000;
	}

}