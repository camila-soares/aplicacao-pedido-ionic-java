package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger ( MockEmailService.class );

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info ( "Simulando envio email..." );
        LOG.info ( msg.toString () );
        LOG.info ( "Email enviado" );
    }

    @Override
    public void sendNewPasswordEmail(Client client , String newPass) {

    }


}
