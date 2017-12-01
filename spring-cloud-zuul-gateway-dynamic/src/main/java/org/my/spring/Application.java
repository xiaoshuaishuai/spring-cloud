package org.my.spring;

import org.my.spring.configuration.FilterConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;

@SpringCloudApplication
@EnableZuulProxy
@EnableConfigurationProperties(value= {FilterConfiguration.class})
public class Application implements ApplicationRunner{
	
//	@Value("${zuul.filter.root}")
//	private String root;
//	@Value("${zuul.filter.interval}")
//	private Integer interval;
	
	@Bean//<bean><property></property></bean>
	FilterLoader filterLoader(FilterConfiguration filterConfiguration  ) {
		FilterLoader loader = FilterLoader.getInstance();
		loader.setCompiler(new GroovyCompiler());
		
		FilterFileManager.setFilenameFilter(new GroovyFileFilter());
		try {//每隔五秒动态加载一次过滤器
			FilterFileManager.init(filterConfiguration.getInterval(), 
					filterConfiguration.getRoot()+"/pre",
					filterConfiguration.getRoot()+"/post");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loader;
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		System.err.println(root);
//		System.err.println(interval);
	}
}
