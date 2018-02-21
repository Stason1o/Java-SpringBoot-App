package com.sbogdanschi.springboot.config;

import com.sbogdanschi.springboot.service.convertors.DtoToRoleConverter;
import com.sbogdanschi.springboot.service.convertors.RoleToDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConversionServiceConfig {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new RoleToDtoConverter());
        service.addConverter(new DtoToRoleConverter());
        return service;
    }
}
