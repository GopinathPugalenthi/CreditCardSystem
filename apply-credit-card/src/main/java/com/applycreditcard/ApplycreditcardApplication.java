package com.applycreditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author javadevopsmc13
 * Main class for apply credit card service
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApplycreditcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplycreditcardApplication.class, args);
	}

}
