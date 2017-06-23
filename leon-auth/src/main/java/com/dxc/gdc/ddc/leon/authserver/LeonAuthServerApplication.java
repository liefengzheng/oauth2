package com.dxc.gdc.ddc.leon.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.dxc.gdc.ddc.leon"})
@EnableFeignClients(basePackages = "com.dxc.gdc.ddc.leon.client")
public class LeonAuthServerApplication {
	 public static void main(String[] args) {
	        SpringApplication.run(LeonAuthServerApplication.class, args);
	    }
}
