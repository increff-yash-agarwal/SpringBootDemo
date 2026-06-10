package com.increff.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.increff.pos")
@EntityScan(basePackages = "com.increff.pos.db") // Explicit path to database entities
@EnableJpaRepositories(basePackages = "com.increff.pos.dao") // Explicit path to query DAOs
@EnableScheduling
public class PosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }
}