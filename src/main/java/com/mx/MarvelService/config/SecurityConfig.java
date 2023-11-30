package com.mx.marvelservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
public class SecurityConfig {

	private String publicKey;
	private String privateKey;
	private String urlMarvenApi;
	private String hash;
	
}
