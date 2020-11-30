package com.xylink.ftp.configuration;

import com.xylink.ftp.configuration.factory.FtpFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-27
 */
@Configuration
public class FtpConfiguration {


    @Bean(initMethod = "init")
    public FtpFactory ftp(){
        String dir = System.getProperty("user.dir");
        return new FtpFactory("anonymous",dir);
    }


}
