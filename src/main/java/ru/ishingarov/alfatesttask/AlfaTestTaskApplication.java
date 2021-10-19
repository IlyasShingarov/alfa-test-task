package ru.ishingarov.alfatesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlfaTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlfaTestTaskApplication.class, args);
    }
}
