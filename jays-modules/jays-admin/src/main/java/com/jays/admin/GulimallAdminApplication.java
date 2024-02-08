package com.jays.admin;

import com.jays.admin.common.core.config.ConfigProperties;
import com.jays.common.swagger.annotation.EnableCustomSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(ConfigProperties.class)
@EnableCustomSwagger
@MapperScan("com.jays.admin.common.system.mapper")
public class GulimallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallAdminApplication.class, args);
    }

}
