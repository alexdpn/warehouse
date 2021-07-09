package com.warehouse.config;

import com.warehouse.repository.ProductRepository;
import com.warehouse.service.ProductService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class AppBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(ProductService.class).to(ProductService.class);
        bind(ProductRepository.class).to(ProductRepository.class);
    }
}
