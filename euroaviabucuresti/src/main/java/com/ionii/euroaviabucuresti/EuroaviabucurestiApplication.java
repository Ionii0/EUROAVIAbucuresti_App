package com.ionii.euroaviabucuresti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class EuroaviabucurestiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EuroaviabucurestiApplication.class, args);
    }



}
