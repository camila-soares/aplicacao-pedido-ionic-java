package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.domain.*;
import com.camilasoares.cursomc.domain.enums.ClientType;
import com.camilasoares.cursomc.domain.enums.EstadoPagamento;
import com.camilasoares.cursomc.domain.enums.Perfil;
import com.camilasoares.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;
	

	public void instatiateTestDatabase() throws ParseException{

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama, messa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoreção");
		Category cat7 = new Category(null, "Perfunamria");


		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "Tv true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pedente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2,p4));
		cat3.getProducts().addAll(Arrays.asList(p5,p6));
		cat4.getProducts().addAll(Arrays.asList(p1,p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9,p10));
		cat7.getProducts().addAll(Arrays.asList(p11));


		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat4));
		p7.getCategories().addAll(Arrays.asList(cat5));
		p8.getCategories().addAll(Arrays.asList(cat6));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat7));
		p11.getCategories().addAll(Arrays.asList(cat7));

		categoryRepository.save(Arrays.asList(cat1, cat2, cat3,cat4, cat5, cat6,cat7));
		productRepository.save(Arrays.asList(p1, p2, p3, p4, p5,  p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais", null);
		Estado est2 = new Estado(null, "São Paulo", null);
		Estado est3 = new Estado ( null, "Pernambuco", null );

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade ( null, "Recife",  est3);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "camila@gmail.com", "36378912377", ClientType.PESSOAFISICA, pe.encode ( "123" ));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Client cli2 = new Client ( null, "Camila Soares", "camilasoare1507@gmail.com", "08146639402", ClientType.PESSOAJURIDICA, pe.encode ( "123" ) );
        cli2.getTelefones ().addAll ( Arrays.asList ( "995346681", "85614242" ) );
		cli2.addPerfil ( Perfil.ADMIN );


		Endereco e1 = new Endereco (null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco (null, "Rua rio apa", "60", "ap 800", "agua-fria", "52211370", cli2, c3);


		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		cli2.getEnderecos ().addAll ( Arrays.asList ( e3 ) );

		clientRepository.save(Arrays.asList(cli1, cli2));
		addressRepository.save(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),  cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),  cli1, e2);

		Payment pagto1 = new PaymentCard(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.save(Arrays.asList(ped1, ped2));
		paymentRepository.save(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));

		
	}
}
