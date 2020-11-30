package com.xylink.ftp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-26
 */
@SpringBootApplication(scanBasePackages = {"com.xylink.ftp"})
public class FtpApiApplication extends SpringBootServletInitializer implements CommandLineRunner {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FtpApiApplication.class);
    }
    public static void main(String[] args)  {
        SpringApplication.run(FtpApiApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        System.out.println(" [ ftp-api ] run success !");
    }
}
