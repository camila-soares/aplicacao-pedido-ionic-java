package com.camilasoares.cursomc.services;



import java.util.Date;

import com.camilasoares.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.camilasoares.cursomc.domain.ItemPedido;
import com.camilasoares.cursomc.domain.PaymentBoleto;
import com.camilasoares.cursomc.domain.Pedido;
import com.camilasoares.cursomc.domain.enums.EstadoPagamento;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;

public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClientRepository clientRepository;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Pedido pedido = pedidoRepository.findOne ( id );
		if(pedido == null) {
			throw new ObjectNotFoundException("Pedido não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return pedido;
	}
	
	
	public Pedido insert(Pedido obj){
		obj.setId(1);
		obj.setInstante(new Date());
		obj.getPayment().setEstado(EstadoPagamento.PENDENTE);
		obj.getPayment().setPedido(obj);
		if(obj.getPayment() instanceof PaymentBoleto){
			PaymentBoleto pagtoBoleto = (PaymentBoleto) obj.getPayment();
			boletoService.preencherPaymentComBoleto(pagtoBoleto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemPedido ip : obj.getItens()){
			ip.setDesconto(0.0);
			ip.setPreco(productRepository.findOne(ip.getProduct().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		System.out.println ( obj );
		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPage, String orderBy, String direction){

		return null;
	}

}
