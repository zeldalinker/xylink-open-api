package com.xylink.wechat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-22
 */
@SpringBootApplication(scanBasePackages = {"com.xylink.*"})
public class WeChatApiApplication extends SpringBootServletInitializer implements CommandLineRunner {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WeChatApiApplication.class);
    }
    public static void main(String[] args)  {
        SpringApplication.run(WeChatApiApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        System.out.println(" third-wechat-api run success !");
    }
}
