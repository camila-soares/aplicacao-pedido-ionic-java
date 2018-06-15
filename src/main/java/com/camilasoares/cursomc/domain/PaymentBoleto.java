package com.camilasoares.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.camilasoares.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PaymentBoleto extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyy")
	private Date dataVencimento;
	@JsonFormat(pattern="dd/MM/yyy")
	private Date dataPagamento;
	
	public PaymentBoleto(){
		
	}

	public PaymentBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
	
}
