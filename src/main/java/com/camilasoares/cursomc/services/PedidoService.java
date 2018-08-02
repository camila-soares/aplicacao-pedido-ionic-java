package com.camilasoares.cursomc.services;



import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.domain.ItemPedido;
import com.camilasoares.cursomc.domain.PaymentBoleto;
import com.camilasoares.cursomc.domain.Pedido;
import com.camilasoares.cursomc.domain.enums.EstadoPagamento;
import com.camilasoares.cursomc.repositories.*;
import com.camilasoares.cursomc.security.UserSS;
import com.camilasoares.cursomc.services.exception.AuthorizationException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
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

	@Autowired
    private EmailService emailService;
	
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
		obj.setClient ( clientRepository.findOne ( obj.getClient ().getId () ) );
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
			ip.setProduct ( productRepository.findOne ( ip.getProduct ().getId () ) );
			ip.setPreco(ip.getProduct ().getPreco ());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		emailService.sendOrderConfirmationEmail ( obj );
		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		UserSS user = UserService.authenticated ();
		if(user == null){
			throw new AuthorizationException ( "Acesso negado" );
		}
		PageRequest pageRequest = new PageRequest ( page, linesPage, Sort.Direction.valueOf ( direction ) , orderBy );
		Client client = clientRepository.findOne ( user.getId () );
		return pedidoRepository.findByClient ( client, pageRequest );
	}

}
