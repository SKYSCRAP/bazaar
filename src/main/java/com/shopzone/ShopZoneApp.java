package com.shopzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.shopzone")
@EntityScan(basePackages = "com.shopzone")
public class ShopZoneApp {
    public static void main(String[] args) {
        SpringApplication.run(ShopZoneApp.class, args);
    }
}