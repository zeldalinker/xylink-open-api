package com.xylink.admin;

import com.xylink.admin.configuration.DataSourceConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author 林骏
 * version: v1
 * date: 2020-12-01
 */
@SpringBootApplication(scanBasePackages = "com.xylink.admin",exclude = {
        DataSourceAutoConfiguration.class
})
public class AdminApiApplication extends SpringBootServletInitializer implements CommandLineRunner {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AdminApiApplication.class);
    }
    public static void main(String[] args)  {
        SpringApplication.run(AdminApiApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        System.out.println(" [ admin-api ] run success !");
    }
}

