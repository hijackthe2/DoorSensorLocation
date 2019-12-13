package com.example.doorsensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
// 扫描被@WebFilter注解的类
// 但在跨域类中注解了@Configuration，所以不需注解@ServletComponentScan
// 否则跨域类会被spring实例化两次
// @ServletComponentScan
public class DoorsensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoorsensorApplication.class, args);
	}

}
