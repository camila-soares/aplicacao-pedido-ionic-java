package com.camilasoares.cursomc.config;

import com.camilasoares.cursomc.services.DBService;
import com.camilasoares.cursomc.services.EmailService;
import com.camilasoares.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value ("create")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException{
		if(! "create".equals(strategy) ){
			return false;
		}
		dbService.instatiateTestDatabase();
		return true;
		
	}

	@Bean
	public EmailService emailService(){
		return new SmtpEmailService ();
	}


}
