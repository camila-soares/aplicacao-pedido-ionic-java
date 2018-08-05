package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

    private static final Logger LOG = LoggerFactory.getLogger ( SmtpEmailService.class );

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info ( "Enviando email..." );
        mailSender.send ( msg );
        LOG.info ( "Email enviado" );
    }

    @Override
    public void sendNewPasswordEmail(Client client , String newPass) {

    }
}
