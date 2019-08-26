package org.n1c0sh.up42cc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Up42ccApplication {

	public static void main(String[] args) {
		SpringApplication.run(Up42ccApplication.class, args);
	}

	@Bean
	ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
