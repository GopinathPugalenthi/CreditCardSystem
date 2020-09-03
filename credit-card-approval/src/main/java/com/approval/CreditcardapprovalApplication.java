package com.approval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author javadevopsmc13
 * Main class for Credit card approval service
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CreditcardapprovalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditcardapprovalApplication.class, args);
	}

}
