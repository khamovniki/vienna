package com.khamovniki.vienna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ViennaApplication {
    public static final String APPLICATION_NAME = "/api";

    public static void main(String[] args) {
        SpringApplication.run(ViennaApplication.class, args);
    }
}
