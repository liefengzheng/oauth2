package com.dxc.gdc.ddc.leon.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AuthSettings.class)
public class AuthConfig {

}
