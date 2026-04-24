package com.resenhita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.security.autoconfigure.web.servlet.SecurityFilterAutoConfiguration;

@SpringBootApplication
public class ResenhitaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResenhitaApplication.class, args);
	}
}
