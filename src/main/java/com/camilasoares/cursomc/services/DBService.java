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
		Product p12 = new Product(null, "produto12", 90.00);
		Product p13 = new Product(null, "produto13", 90.00);
		Product p14 = new Product(null, "produto14", 90.00);
		Product p15 = new Product(null, "produto15", 90.00);
		Product p16 = new Product(null, "produto16", 90.00);
		Product p17 = new Product(null, "produto17", 90.00);
		Product p18 = new Product(null, "produto18", 90.00);
		Product p19 = new Product(null, "produto19", 90.00);
		Product p20 = new Product(null, "produto20", 90.00);
		Product p21 = new Product(null, "produto21", 90.00);
		Product p22 = new Product(null, "produto22", 90.00);
		Product p23 = new Product(null, "produto23", 90.00);
		Product p24 = new Product(null, "produto24", 90.00);
		Product p25 = new Product(null, "produto25", 90.00);
		Product p26 = new Product(null, "produto26", 90.00);
		Product p27 = new Product(null, "produto27", 90.00);
		Product p28 = new Product(null, "produto28", 90.00);
		Product p29 = new Product(null, "produto29", 90.00);
		Product p30 = new Product(null, "produto30", 90.00);
		Product p31 = new Product(null, "produto31", 90.00);
		Product p32 = new Product(null, "produto32", 90.00);
		Product p33 = new Product(null, "produto33", 90.00);
		Product p34 = new Product(null, "produto34", 90.00);
		Product p35 = new Product(null, "produto35", 90.00);
		Product p36 = new Product(null, "produto36", 90.00);
		Product p37 = new Product(null, "produto37", 90.00);
		Product p38 = new Product(null, "produto38", 90.00);
		Product p39 = new Product(null, "produto39", 90.00);
		Product p40 = new Product(null, "produto40", 90.00);
		Product p41 = new Product(null, "produto41", 90.00);
		Product p42 = new Product(null, "produto42", 90.00);
		Product p43 = new Product(null, "produto43", 90.00);
		Product p44 = new Product(null, "produto44", 90.00);
		Product p45 = new Product(null, "produto45", 90.00);
		Product p46 = new Product(null, "produto46", 90.00);
		Product p47 = new Product(null, "produto47", 90.00);
		Product p48 = new Product(null, "produto48", 90.00);
		Product p49 = new Product(null, "produto49", 90.00);
		Product p50 = new Product(null, "produto50", 90.00);

		cat1.getProdutos ().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));


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
		p12.getCategories ().add(cat1);
		p1.getCategories ().add(cat1);
		p14.getCategories ().add(cat1);
		p15.getCategories ().add(cat1);
		p16.getCategories ().add(cat1);
		p17.getCategories ().add(cat1);
		p18.getCategories ().add(cat1);
		p19.getCategories ().add(cat1);
		p20.getCategories ().add(cat1);
		p21.getCategories ().add(cat1);
		p22.getCategories ().add(cat1);
		p23.getCategories ().add(cat1);
		p24.getCategories ().add(cat1);
		p25.getCategories ().add(cat1);
		p26.getCategories ().add(cat1);
		p27.getCategories ().add(cat1);
		p28.getCategories ().add(cat1);
		p29.getCategories ().add(cat1);
		p30.getCategories ().add(cat1);
		p31.getCategories ().add(cat1);
		p32.getCategories ().add(cat1);
		p33.getCategories ().add(cat1);
		p34.getCategories ().add(cat1);
		p35.getCategories ().add(cat1);
		p36.getCategories ().add(cat1);
		p37.getCategories ().add(cat1);
		p38.getCategories ().add(cat1);
		p39.getCategories ().add(cat1);
		p40.getCategories ().add(cat1);
		p41.getCategories ().add(cat1);
		p42.getCategories ().add(cat1);
		p43.getCategories ().add(cat1);
		p44.getCategories ().add(cat1);
		p45.getCategories ().add(cat1);
		p46.getCategories ().add(cat1);
		p47.getCategories ().add(cat1);
		p48.getCategories ().add(cat1);
		p49.getCategories ().add(cat1);
		p50.getCategories ().add(cat1);

		categoryRepository.saveAll (Arrays.asList(cat1, cat2, cat3,cat4, cat5, cat6,cat7));
		productRepository.saveAll (Arrays.asList(p1, p2, p3, p4, p5,  p6, p7, p8, p9, p10, p11));

		productRepository.saveAll (Arrays.asList (p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50 ));

		Estado est1 = new Estado(null, "Minas Gerais", null);
		Estado est2 = new Estado(null, "São Paulo", null);
		Estado est3 = new Estado ( null, "Pernambuco", null );

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade ( null, "Recife",  est3);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll (Arrays.asList(est1, est2));
		cidadeRepository.saveAll (Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "camila@gmail.com", "36378912377", ClientType.PESSOAFISICA, pe.encode ( "123" ));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		cli1.addPerfil ( Perfil.CLIENTE );

		Client cli2 = new Client ( null, "Camila Soares", "camilasoares1507@gmail.com", "08146639402", ClientType.PESSOAJURIDICA, pe.encode ( "123" ) );
        cli2.getTelefones ().addAll ( Arrays.asList ( "995346681", "85614242" ) );
		cli2.addPerfil ( Perfil.ADMIN );


		Endereco e1 = new Endereco (null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco (null, "Rua rio apa", "60", "ap 800", "agua-fria", "52211370", cli2, c3);


		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		cli2.getEnderecos ().addAll ( Arrays.asList ( e3 ) );

		clientRepository.saveAll (Arrays.asList(cli1, cli2));
		addressRepository.saveAll (Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),  cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),  cli1, e2);

		Payment pagto1 = new PaymentCard(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll (Arrays.asList(ped1, ped2));
		paymentRepository.saveAll (Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll (Arrays.asList(ip1, ip2, ip3));

		
	}
}
