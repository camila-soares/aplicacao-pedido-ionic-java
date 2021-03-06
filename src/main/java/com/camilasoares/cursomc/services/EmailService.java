package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Client client, String newPass);
}
