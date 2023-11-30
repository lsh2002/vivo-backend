package com.lsh.vivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author lsh
 */
@SpringBootApplication
@EnableScheduling
public class XiaomiMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaomiMallApplication.class, args);
    }
}