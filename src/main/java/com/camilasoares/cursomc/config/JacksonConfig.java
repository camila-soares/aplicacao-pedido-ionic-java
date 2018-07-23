package com.camilasoares.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.camilasoares.cursomc.domain.PaymentBoleto;
import com.camilasoares.cursomc.domain.PaymentCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder(){
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(){
			public void configure(ObjectMapper objectMapper){
				objectMapper.registerSubtypes(PaymentCard.class);
				objectMapper.registerSubtypes(PaymentBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
		
	}
}
