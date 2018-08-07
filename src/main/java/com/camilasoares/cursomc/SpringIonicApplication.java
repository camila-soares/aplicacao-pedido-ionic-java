package com.camilasoares.cursomc;

import com.camilasoares.cursomc.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;


	public static void main(String[] args) {SpringApplication.run(SpringIonicApplication.class, args);}




	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile ( "C:\\tmp\\archive\\images.jpg"  );
	}


}
