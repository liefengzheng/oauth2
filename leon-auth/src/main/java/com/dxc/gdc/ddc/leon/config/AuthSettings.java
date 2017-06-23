package com.dxc.gdc.ddc.leon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "foxauth.token.settings")
public class AuthSettings {
    private int validity_seconds = 1000;
}
