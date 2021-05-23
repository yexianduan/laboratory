package com.yexianduan.laboratory.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yolanda
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LaboratoryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratoryGatewayApplication.class, args);
	}


}
