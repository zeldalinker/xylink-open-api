package com.xylink.wechat.config;

import com.xylink.wechat.config.factory.executor.ThreadPoolMdcWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * @author 林骏
 * version: v1
 * date: 2020-11-15
 */
@Configuration
@EnableAsync
public class ThreadPoolConfiguration {

    @Bean("mdcThreadPool")
    public ThreadPoolMdcWrapper mdcThreadPool(){
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);
        return new ThreadPoolMdcWrapper(50,
                100,
                60*30,
                TimeUnit.SECONDS,
                workQueue,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }


}
