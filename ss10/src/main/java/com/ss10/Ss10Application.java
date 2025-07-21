package com.ss10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Ss10Application{

    public static void main(String[] args){
        SpringApplication.run(Ss10Application.class, args);
    }

}
