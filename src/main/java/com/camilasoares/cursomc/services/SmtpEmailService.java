package com.camilasoares.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

<<<<<<< HEAD

    private static final Logger LOG = LoggerFactory.getLogger ( SmtpEmailService.class );
=======
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
>>>>>>> cd631e18e711341970616402ccb917708cd3eeb7

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando email...");
        mailSender.send(msg);
        LOG.info("Email enviado");
    }
}
