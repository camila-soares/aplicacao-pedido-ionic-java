package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;


import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getClient ().getEmail ());
        sm.setFrom(sender);
        sm.setSubject("Pedido Confirmado! Código: " + obj.getId ());
        sm.setSentDate(new Date(System.currentTimeMillis ()) );
        sm.setText(obj.toString ());
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(Client client, String newPass){
        SimpleMailMessage sm = prepareNewPasswordEmail ( client , newPass);
        sendEmail ( sm );
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(client.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }

}
