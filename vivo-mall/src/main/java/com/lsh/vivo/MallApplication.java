package com.lsh.vivo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author lsh
 */
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
@ServletComponentScan
@MapperScan({"com.lsh.vivo.mapper"})
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }
}