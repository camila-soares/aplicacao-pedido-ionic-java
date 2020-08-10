package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.repositories.ClientRepository;
import com.camilasoares.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;


    private Random rand = new Random ();

    public void sendNewPassword(String email) {

        Client client = clientRepository.findByEmail ( email );
        if (client == null) {
            throw new ObjectNotFoundException ( "Email não encontrado " );
        }
        System.out.println ( "email enviado" );
        String newPass = newPassword ();
        client.setSenha ( bCryptPasswordEncoder.encode ( newPass ) );

        clientRepository.save ( client );

        emailService.sendNewPasswordEmail ( client , newPass );
        System.out.println ( "email enviado------  " + newPass);
    }
    
    public void createPasswordResetTokenForUser(Client client, String token) {
    	
    }

    private String newPassword() {
    	String token = UUID.randomUUID().toString();
		return token;
    }

}
