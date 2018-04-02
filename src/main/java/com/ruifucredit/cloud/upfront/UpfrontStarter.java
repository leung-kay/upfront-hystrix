package com.ruifucredit.cloud.upfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class UpfrontStarter {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(UpfrontStarter.class, args);
	}
	
}
