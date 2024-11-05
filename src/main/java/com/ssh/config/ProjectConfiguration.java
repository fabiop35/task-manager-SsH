package com.ssh.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableFeignClients(basePackages = "com.ssh.proxies")
@ComponentScan(basePackages = {"com.ssh.proxies", "com.ssh.services", "com.ssh.repositories", "com.ssh.processors", "com.ssh.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfiguration {
}
