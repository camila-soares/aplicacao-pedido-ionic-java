package com.camilasoares.cursomc.config;

import com.camilasoares.cursomc.services.DBService;
import com.camilasoares.cursomc.services.EmailService;
import com.camilasoares.cursomc.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;

	@Bean
	public boolean instatiateDatabase() throws ParseException{
		dbService.instatiateTestDatabase();
		return true;
		
	}

	@Bean
	public EmailService emailService(){
		return new MockEmailService ();
	}
}
