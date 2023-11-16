package com.helpfinance.api;

import java.sql.Connection;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.helpfinance.infrastructure.contexts.helpFinanceContext;

@Configuration
@ComponentScan(basePackages = "com.helpfinance")
public class AppConfig {

    @Bean
    @ConfigurationProperties(prefix = "java.sql.connection")
    @Scope("singleton")
    Connection connection() {
        return helpFinanceContext.getConnection();
    }
}
