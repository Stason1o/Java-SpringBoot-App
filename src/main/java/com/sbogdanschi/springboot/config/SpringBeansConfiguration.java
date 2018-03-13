package com.sbogdanschi.springboot.config;

import com.sbogdanschi.springboot.service.convertors.DtoToRoleConverter;
import com.sbogdanschi.springboot.service.convertors.RoleToDtoConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringBeansConfiguration {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new RoleToDtoConverter());
        service.addConverter(new DtoToRoleConverter());
        return service;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
