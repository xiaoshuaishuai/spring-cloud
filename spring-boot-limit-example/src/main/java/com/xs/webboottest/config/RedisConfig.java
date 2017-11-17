package com.xs.webboottest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

@Configuration
public class RedisConfig {
	@Value("${spring.redis.nodes}")
	private String nodes;
	@Value("${spring.redis.timeout}")
	private int timeout;
	@Value("${spring.redis.pool.max-active}")
	private int maxActive;
	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;
	@Value("${spring.redis.pool.max-wait}")
	private long maxWait;
	@Value("${spring.redis.pool.min-idle}")
	private int minIdle;

//	@Bean
//	public RedisClusterConfiguration redisClusterConfiguration() {
//		RedisClusterConfiguration configuration = null;
//		if (cluster == null) {
//			configuration = new RedisClusterConfiguration();
//			String[] nodeArr = nodes.split(",");
//			for (String node : nodeArr) {
//				String[] ipPort = node.split(":");
//				configuration.addClusterNode(new RedisNode(ipPort[0].trim(), Integer.valueOf(ipPort[1].trim())));
//			}
//		}
//		return configuration;
//	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(
			JedisPoolConfig poolConfig) {
		JedisConnectionFactory factory = null;
		String[] ipPort = nodes.split(":");
		JedisShardInfo shardInfo = new JedisShardInfo(ipPort[0].trim(), Integer.valueOf(ipPort[1].trim()));
		factory = new JedisConnectionFactory(shardInfo);
		factory.setTimeout(timeout);
		return factory;
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWait);
		poolConfig.setMaxTotal(maxActive);
		return poolConfig;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory factory) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		StringRedisTemplate template = null;
		template = new StringRedisTemplate(factory);
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(
				mapper);
		template.setValueSerializer(genericJackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
}
