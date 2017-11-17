package com.xs.webboottest.limiting;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.xs.webboottest.vo.RequestVo;

@Component
public class FlowLimitImpl implements IFlowLimit {

    
	private static final Logger logger = LoggerFactory.getLogger(FlowLimitImpl.class);

	private static final String limits = "dev:limits:{0}";
	private static final String risks = "dev:risk:{0}";
	private static final Map<String, FlowStatus> map = new ConcurrentHashMap<>();
	
	
	@Value("${web.test.limit.riskCode}")
	private  String riskCode;
	@Value("${web.test.limit.riskPermit}")  
	private  String riskPermit;
	@Value("${web.test.limit.permit}")
	private  String permit;
	@Value("${web.test.limit.permitUnit}")
	private  String permitUnit;
	@Value("${web.test.limit.interfaceId}") 
	private  String interfaceId;
	

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private String luaScript;

	@Override
	public FlowStatus limit(RequestVo requestVo) {
		getRedisFlowStatus();
		String interfaceId = requestVo.getInterfaceId();// 请求接口
		/**
		 * 限流key 预警key
		 */
		final String limitsKey = MessageFormat.format(limits, interfaceId);
		final String risksKey = MessageFormat.format(risks, interfaceId);
//		logger.info("interfaceId:"+interfaceId+",limitsKey:"+limitsKey+",risksKey:"+risksKey);
		this.luaScript = getFlowLimitLuaScript();
		final FlowStatus flowStatus = map.get(interfaceId);
		if (null != flowStatus) {
			final Long result = redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					return (Long) connection.eval(luaScript.getBytes(), ReturnType.INTEGER, 2, limitsKey.getBytes(),
							risksKey.getBytes(), permit.getBytes(), permitUnit.getBytes(), riskPermit.getBytes());
				}
			});

			if (result < 0) {//-负数 ,证明大于并发数  lua
				flowStatus.setRefuse(true);
				flowStatus.setCurrCount(-result);
			} else if (result > 0) {//触发了预警值
				flowStatus.setRisk(true);
				flowStatus.setCurrCount(result);
			}
		}
		return flowStatus;
	}

	private String getFlowLimitLuaScript() {
		final StringBuilder builder = new StringBuilder();
		builder.append(" local permit, permitUnit, riskPermit = ARGV[1], ARGV[2], ARGV[3] ");
		builder.append(" if (permit~='' and permitUnit~='') then ");
		builder.append(" if redis.call('setnx', KEYS[1], 0)==1 then ");
		builder.append(" redis.call('expire', KEYS[1], permitUnit) ");
		builder.append(" end ");
		builder.append(" local currPermit = redis.call('incr', KEYS[1]) ");
		builder.append(" if(currPermit > tonumber(permit)) then ");
		builder.append(" return -currPermit ");
		builder.append(" end ");
		builder.append(" end ");
		builder.append(" if riskPermit~='' then ");
		builder.append(" if redis.call('setnx', KEYS[2], 0)==1 then ");
		builder.append(" redis.call('expire', KEYS[2], 1) ");
		builder.append(" end ");
		builder.append(" local currRiskPermit = redis.call('incr', KEYS[2]) ");
		builder.append(" if currRiskPermit > tonumber(riskPermit) then ");
		builder.append(" return currRiskPermit ");
		builder.append(" end ");
		builder.append(" end ");
		builder.append(" return 0 ");
		return builder.toString();
	}
	/**
	 * 获取限流的信息
	 */
	private  FlowStatus getRedisFlowStatus() {
		// 这里根据需要可以从缓存 or 数据库加载，做到动态
		FlowStatus reqStatus = new FlowStatus();
		reqStatus.setPermit(permit);
		reqStatus.setPermitUnit(permitUnit);
		reqStatus.setRiskCode(riskCode);
		reqStatus.setRiskPermit(riskPermit);
		reqStatus.setInterfaceId(interfaceId);
		map.put(interfaceId, reqStatus);
		return reqStatus;
	}

}
