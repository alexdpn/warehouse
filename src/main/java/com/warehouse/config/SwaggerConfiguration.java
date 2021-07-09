package com.warehouse.config;

import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerConfiguration {

    public void configureSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8081");
        beanConfig.setResourcePackage("com.warehouse.resource");
        beanConfig.setScan(true);
    }
}
