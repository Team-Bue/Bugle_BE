package com.example.bugle_be.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "elasticsearchAsyncExecutor")
    public ThreadPoolTaskExecutor elasticsearchAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("es-async-");

        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

    @Bean(name = "mailAsyncExecutor")
    public ThreadPoolTaskExecutor mailAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("mail-async-");

        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return mailAsyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                log.error("Async error in method: {}", method.getName(), ex);
            }
        };
    }
}
