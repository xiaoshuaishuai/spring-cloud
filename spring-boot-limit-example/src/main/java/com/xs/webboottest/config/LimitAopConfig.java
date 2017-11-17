package com.xs.webboottest.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.xs.webboottest.limiting.FlowStatus;
import com.xs.webboottest.limiting.IFlowLimit;
import com.xs.webboottest.vo.RequestVo;

/**
 * 接口限流aop
 */
@Aspect
@Component
public class LimitAopConfig implements Ordered{
	private static final Logger logger = LoggerFactory.getLogger(LimitAopConfig.class);

	@Autowired
	private IFlowLimit flowLimit;
	
    @Around("@annotation(com.xs.webboottest.limiting.limit)")
    public Object flowLimit(final ProceedingJoinPoint point) throws Throwable {
//    	logger.info("limit主键被拦截");
        final Object[] args = point.getArgs();
        final RequestVo requestVo = (RequestVo)args[0];
        final FlowStatus flowStatus = this.flowLimit.limit(requestVo);
        if (null != flowStatus) {
            if (flowStatus.isRefuse()) {
            	logger.info("拒绝服务...," +flowStatus.getCurrCount()+","+ flowStatus.getPermit()+","+ flowStatus.getPermitUnit());
                throw new Exception(String.format("拒绝服务...", flowStatus.getCurrCount(), flowStatus.getPermit(), flowStatus.getPermitUnit()).toString());
            }
            if (flowStatus.isRisk()) {
            	logger.info("预警消息..," +flowStatus.getCurrCount()+","+ flowStatus.getPermit()+","+ flowStatus.getPermitUnit());
            }
        }
        return point.proceed(args);
    }
	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

}
