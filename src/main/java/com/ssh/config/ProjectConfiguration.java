package com.ssh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableFeignClients(basePackages = "com.ssh.proxies")
@ComponentScan(basePackages = {"com.ssh.proxies", "com.ssh.services", "com.ssh.repositories", "com.ssh.processors", "com.ssh.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfiguration {

    @Autowired
    Environment environment;

    private final String URL = "custom.spring.datasource.url";
    private final String USER = "custom.spring.datasource.username";
    private final String PASSWORD = "custom.spring.datasource.password";

    @Bean
    DataSource dataSource() {
        HikariDataSource driverManagerDataSource = new HikariDataSource();
        driverManagerDataSource.setJdbcUrl(environment.getProperty(URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER));
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));

        return driverManagerDataSource;
    }
}
