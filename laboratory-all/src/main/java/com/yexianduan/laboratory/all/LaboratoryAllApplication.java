package com.yexianduan.laboratory.all;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.yexianduan.laboratory")
public class LaboratoryAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoryAllApplication.class, args);
    }

}
