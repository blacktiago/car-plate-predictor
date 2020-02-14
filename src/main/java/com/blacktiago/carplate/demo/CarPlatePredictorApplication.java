package com.blacktiago.carplate.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CarPlatePredictorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CarPlatePredictorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < args.length; ++i) {
            log.info("args[{}]: {}", i, args[i]);
        }
    }
}
