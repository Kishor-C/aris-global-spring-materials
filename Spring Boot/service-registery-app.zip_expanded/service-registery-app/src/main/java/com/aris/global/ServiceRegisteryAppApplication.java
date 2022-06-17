package com.aris.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegisteryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegisteryAppApplication.class, args);
	}

}
