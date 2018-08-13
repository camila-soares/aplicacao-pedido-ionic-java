package com.camilasoares.cursomc.services;



import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.domain.ItemPedido;
import com.camilasoares.cursomc.domain.PaymentBoleto;
import com.camilasoares.cursomc.domain.Pedido;
import com.camilasoares.cursomc.domain.enums.EstadoPagamento;
import com.camilasoares.cursomc.repositories.ItemPedidoRepository;
import com.camilasoares.cursomc.repositories.PaymentRepository;
import com.camilasoares.cursomc.repositories.PedidoRepository;
import com.camilasoares.cursomc.security.UserSS;
import com.camilasoares.cursomc.services.exception.AuthorizationException;
import com.camilasoares.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	

	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;


	@Autowired
	private ClientService clientService;

	@Autowired
    private EmailService emailService;


	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> pedido = pedidoRepository.findById ( id );
		return pedido.orElseThrow ( () -> new ObjectNotFoundException (
				"Objeto não encontrado!" + id + ", Tipo:" + Pedido.class.getName ()
		) );
	}
	
	
	public Pedido insert(Pedido obj) throws ObjectNotFoundException {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setClient ( clientService.find ( obj.getClient ().getId () ) );
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
			ip.setProduct ( productService.find ( ip.getProduct ().getId () ) );
			ip.setPreco(ip.getProduct ().getPreco ());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll (obj.getItens());
		emailService.sendOrderConfirmationEmail ( obj );
		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		UserSS user = UserService.authenticated ();
		if(user == null){
			throw new AuthorizationException ( "Acesso negado" );
		}
		PageRequest pageRequest = new PageRequest ( page, linesPage, Sort.Direction.valueOf ( direction ) , orderBy );
		Client client = clientService.find ( user.getId () );
		return pedidoRepository.findByClient ( client, pageRequest );
	}

}
