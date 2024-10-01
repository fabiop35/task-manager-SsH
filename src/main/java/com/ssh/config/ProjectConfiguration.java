package com.ssh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.ssh.proxies", "com.ssh.services", "com.ssh.repositories", "com.ssh.processors"})
public class ProjectConfiguration {
}
