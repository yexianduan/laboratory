package com.yexianduan.laboratory.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yolanda
 */
@SpringBootApplication
@MapperScan("com.yexianduan.laboratory.auth.mapper")
public class LaboratoryAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratoryAuthApplication.class, args);
	}

}
