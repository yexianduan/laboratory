package com.yexianduan.laboratory.teacher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yexianduan.laboratory")
public class LaboratoryTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoryTeacherApplication.class, args);
    }

}
