package org.my.spring.filter.cust;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class CustFilterProcessor extends FilterProcessor{
	private static final Logger logger = LoggerFactory.getLogger(CustFilterProcessor.class);

	@Override
	public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
		logger.info("processZuulFilter*****");
		try {
			return super.processZuulFilter(filter);
		} catch (ZuulException e) {
			logger.info("processZuulFilter异常执行*****");
			RequestContext context = RequestContext.getCurrentContext();
			context.set("filter.cust.error", filter);
			throw e;
		}
	}
}
