package com.dxc.gdc.ddc.leon.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.dxc.gdc.ddc.leon.edge.filter.AccessFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
@ComponentScan(basePackages = {"com.dxc.gdc.ddc.leon"})
@EnableFeignClients(basePackages = "com.dxc.gdc.ddc.leon.client")
public class EdgeApplication {
	public static void main(String[] args) {
        SpringApplication.run(EdgeApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
