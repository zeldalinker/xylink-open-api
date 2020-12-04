package com.xylink.deploy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-02
 */
@SpringBootApplication
public class Application  {

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }

}

