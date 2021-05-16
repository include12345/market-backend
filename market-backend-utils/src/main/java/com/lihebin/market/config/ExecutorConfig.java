package com.lihebin.market.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by lihebin on 2021/5/16.
 */
@Slf4j
@Configuration
public class ExecutorConfig {


    @Bean
    public Executor commonExecutor() {
        log.info("start executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);  //配置核心线程数
        executor.setMaxPoolSize(10); //配置最大线程数
        executor.setQueueCapacity(1000); //配置队列大小
        executor.setThreadNamePrefix("Thread-"); //配置线程池中线程名称前缀
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
