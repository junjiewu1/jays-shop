package com.gulimall.common.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
public class SwaggerAutoConfiguration {
    @Bean
    public OpenAPI springOpenAPI(SwaggerProperties swaggerProperties){
        return new OpenAPI().info(info(swaggerProperties));
    }

    public Info info(SwaggerProperties swaggerProperties){
        return new Info().title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .license(new License().name(swaggerProperties.getLicense()).url(swaggerProperties.getLicenseUrl()))
                .termsOfService(swaggerProperties.getTermsOfServiceUrl())
                .contact(swaggerProperties.getContact())
                .version(swaggerProperties.getVersion())
                ;
    }
}
