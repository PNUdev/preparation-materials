package com.pnudev.RestSecurityBasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class RestSecurityBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSecurityBasicApplication.class, args);
	}

}
