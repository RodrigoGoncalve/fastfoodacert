package br.com.fastfoodacert.groupacert.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
