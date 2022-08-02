package ru.inobitec.taskone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TaskOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskOneApplication.class, args);
    }

}
