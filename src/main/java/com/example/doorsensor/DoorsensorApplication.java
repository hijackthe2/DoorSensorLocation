package com.example.doorsensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //扫描被@WebFilter注解的类
public class DoorsensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoorsensorApplication.class, args);
	}

}
