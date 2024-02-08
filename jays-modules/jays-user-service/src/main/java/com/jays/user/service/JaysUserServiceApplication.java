package com.jays.user.service;

import com.jays.common.swagger.annotation.EnableCustomSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jays.user.service.mapper")
@EnableCustomSwagger
public class JaysUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaysUserServiceApplication.class, args);
	}

}
