package com.camilasoares.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;


@SpringBootApplication
//ComponentScan({"com.camilasoares.cursomc.resouces"})
@ComponentScan({"com.camilasoares.cursomc.services"})
public class SpringIonicApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
	
	
}
