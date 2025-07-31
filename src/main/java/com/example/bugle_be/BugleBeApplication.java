package com.example.bugle_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BugleBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BugleBeApplication.class, args);
    }

}