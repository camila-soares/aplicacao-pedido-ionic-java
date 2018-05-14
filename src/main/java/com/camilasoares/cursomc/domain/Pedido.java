package com.camilasoares.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	 

	private Integer id;
	private Date instante;
	
	private Payment payment;
	
	private Client client;
	
	private Adress enderecoDeEntrega;
	
	public Pedido(){
		
	}

	public Pedido(Integer id, Date instante, Payment payment, Client client, Adress enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.payment = payment;
		this.client = client;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Adress getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Adress enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
