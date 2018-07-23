package com.camilasoares.cursomc.services;


import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.camilasoares.cursomc.domain.PaymentBoleto;

@Service
public class BoletoService {
	
	public void preencherPaymentComBoleto(PaymentBoleto pagamentoBoleto, Date instanteDoPedido){
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoBoleto.setDataVencimento(cal.getTime());
	}

}
