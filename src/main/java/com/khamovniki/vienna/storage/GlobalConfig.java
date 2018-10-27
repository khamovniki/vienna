package com.khamovniki.vienna.storage;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = GlobalConfig.class)
@EnableJpaRepositories(basePackageClasses =  GlobalConfig.class)
public class GlobalConfig {
}
