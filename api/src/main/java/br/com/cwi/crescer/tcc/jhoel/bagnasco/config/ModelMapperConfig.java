package br.com.cwi.crescer.tcc.jhoel.bagnasco.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper config() {
        return new ModelMapper();
    }
}
