package com.pentaproject.demobackend.Config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    //Metodo per eliminare i valori nulli dal json response
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer(){
        return builder -> builder
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializers();
    }
}
