package com.camilasoares.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	@Id
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyy hh:mm")
	private Date instante;
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Payment payment;
	
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="endereco")
	private Adress enderecoDeEntrega;
	
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Pedido(){}

	public Pedido(Long id, Date instante,  Client client, Adress enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.client = client;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Long getId() {return id;}

	public void setId(Long id) {	this.id = id;}

	public Date getInstante() {	return instante; }

	public void setInstante(Date instante) { this.instante = instante;	}

	public Payment getPayment() { return payment;	}

	public void setPayment(Payment payment) { this.payment = payment;	}

	public Client getClient() {		return client;	}

	public void setClient(Client client) { this.client = client;	}

	public Adress getEnderecoDeEntrega() { return enderecoDeEntrega;	}

	public void setEnderecoDeEntrega(Adress enderecoDeEntrega) {this.enderecoDeEntrega = enderecoDeEntrega;}
	
	public Set<ItemPedido> getItens() {return itens;}

	public void setItens(Set<ItemPedido> itens) {	this.itens = itens;}
	

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
