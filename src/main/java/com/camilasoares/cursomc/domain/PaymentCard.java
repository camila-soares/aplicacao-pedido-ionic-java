package com.camilasoares.cursomc.domain;

import com.camilasoares.cursomc.domain.enums.EstadoPagamento;

public class PaymentCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PaymentCard(){
		
	}

	public PaymentCard(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
	
}
