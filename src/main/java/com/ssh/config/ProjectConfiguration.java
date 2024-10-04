package com.ssh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.ssh.proxies", "com.ssh.services", "com.ssh.repositories", "com.ssh.processors", "com.ssh.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfiguration {
}
