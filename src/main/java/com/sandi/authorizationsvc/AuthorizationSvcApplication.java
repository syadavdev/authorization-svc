package com.sandi.authorizationsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication( scanBasePackages = {"com.sandi.authorizationsvc" ,"com.sandi.commonsvc"})
@EnableDiscoveryClient
public class AuthorizationSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationSvcApplication.class, args);
	}

}
