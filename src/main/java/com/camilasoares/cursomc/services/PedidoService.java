package com.camilasoares.cursomc.services;



import java.util.Date;

import com.camilasoares.cursomc.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.camilasoares.cursomc.domain.ItemPedido;
import com.camilasoares.cursomc.domain.PaymentBoleto;
import com.camilasoares.cursomc.domain.Pedido;
import com.camilasoares.cursomc.domain.enums.EstadoPagamento;
import com.camilasoares.cursomc.repositories.PaymentRepository;
import com.camilasoares.cursomc.repositories.PedidoRepository;
import com.camilasoares.cursomc.repositories.ProductRepository;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;

public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Pedido pedido = repo.findOne ( id );
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
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemPedido ip : obj.getItens()){
			ip.setDesconto(0.0);
			ip.setPreco(productRepository.findOne(ip.getProduct().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPage, String orderBy, String direction){

		return null;
	}

}
