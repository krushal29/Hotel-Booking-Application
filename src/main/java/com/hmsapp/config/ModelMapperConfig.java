package com.hmsapp.config;

import com.hmsapp.entity.Property;
import com.hmsapp.payload.PropertyDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper(); // no typeMap, no confusion
    }
}
