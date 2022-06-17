package com.aris.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyFirstMicroserviceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstMicroserviceAppApplication.class, args);
	}

}
