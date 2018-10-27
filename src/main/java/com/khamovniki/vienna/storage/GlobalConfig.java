package com.khamovniki.vienna.storage;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = GlobalConfig.class)
@EnableJpaRepositories(basePackageClasses =  GlobalConfig.class)
public class GlobalConfig {

    private static final String BOT_URI = "http://localhost:8090/bot";

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }

    @Bean(name = "botRestTemplate")
    public RestTemplate botRestTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder
                .rootUri(BOT_URI)
                .build();
    }
}
