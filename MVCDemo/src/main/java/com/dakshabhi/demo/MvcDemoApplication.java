package com.dakshabhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "com.dakshabhi.demo")
@SpringBootApplication
public class MvcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcDemoApplication.class, args);
	}

}
