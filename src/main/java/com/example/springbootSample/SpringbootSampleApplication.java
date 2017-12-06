package com.example.springbootSample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

		/*
* SpringBoot的入口类
*
* @SpringBootApplication是组合注解，包含如下3个注解
* 	@Configuration：表明当前类是一个配置类
* 	@EnableAutoConfiguration：表示让SpringBoot根据类路径中的jar包依赖为当前项目进行自动配置
*	@ComponentScan：表明需要Spring自动扫描并管理的类
*
* 注意：使用了@SpringBootApplication注解的话，系统会去入口类的同级包以及下级包中去扫描实体类，因此我们建议入口类的位置在groupId+arctifactID组合的包名下。
*
* */

@SpringBootApplication
@MapperScan("com.example.springbootSample.mapper") //指定要扫描的Mapper类的包的路径
@EnableTransactionManagement //开启注解式事务的支持，以便使用@Transactional注解
public class SpringbootSampleApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSampleApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootSampleApplication.class);
	}
}
