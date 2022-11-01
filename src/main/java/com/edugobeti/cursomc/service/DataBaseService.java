package com.edugobeti.cursomc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edugobeti.cursomc.domain.Adress;
import com.edugobeti.cursomc.domain.Category;
import com.edugobeti.cursomc.domain.City;
import com.edugobeti.cursomc.domain.Client;
import com.edugobeti.cursomc.domain.CreditPayment;
import com.edugobeti.cursomc.domain.Order1;
import com.edugobeti.cursomc.domain.OrderItem;
import com.edugobeti.cursomc.domain.Payment;
import com.edugobeti.cursomc.domain.Product;
import com.edugobeti.cursomc.domain.State;
import com.edugobeti.cursomc.domain.TicketPayment;
import com.edugobeti.cursomc.domain.enuns.ClientType;
import com.edugobeti.cursomc.domain.enuns.PaymentStatus;
import com.edugobeti.cursomc.repository.AdressRepository;
import com.edugobeti.cursomc.repository.CategoryRepository;
import com.edugobeti.cursomc.repository.CityRepository;
import com.edugobeti.cursomc.repository.ClientRepository;
import com.edugobeti.cursomc.repository.Order1Repository;
import com.edugobeti.cursomc.repository.OrderItemRepository;
import com.edugobeti.cursomc.repository.PaymentRepository;
import com.edugobeti.cursomc.repository.ProductRepository;
import com.edugobeti.cursomc.repository.StateRepository;

@Service
public class DataBaseService {
	
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AdressRepository adressRepository;
	
	@Autowired
	Order1Repository orderRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	OrderItemRepository	orderItemRepository;
	
	public void instantiateDataBase() throws ParseException{

		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "Tv true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
	
		State stt1 = new State(null, "Minas Gerais");
		State stt2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", stt1);
		City c2 = new City(null, "São Paulo", stt2);
		City c3 = new City(null, "Campinas", stt2);
		
		stt1.getCities().addAll(Arrays.asList(c1));
		stt2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(stt1, stt2));
	    cityRepository.saveAll(Arrays.asList(c1, c2, c3));
	    
	    Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "35226554885", ClientType.PESSOAFISICA);
	    
	    cli1.getPhone().addAll(Arrays.asList("35662595", "99896354"));
	    
	    Adress a1 = new Adress(null, "Rua Flores", "300", "apartamento 203", "Bairro Jardim", "13455678", cli1, c1);
	    Adress a2 = new Adress(null, "Av Matos", "103", "Sala 800", "Centro", "01258336", cli1, c2);
	    
	    cli1.getAdresses().addAll(Arrays.asList(a1, a2));
		
	    clientRepository.saveAll(Arrays.asList(cli1));
	    
	    adressRepository.saveAll(Arrays.asList(a1, a2));
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
	   
	    Order1 o1 = new Order1(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
	    Order1 o2 = new Order1(null, sdf.parse("10/10/2017 19:35"), cli1, a2);
	
	    cli1.getOrders().addAll(Arrays.asList(o1, o2));
	   
	    Payment pay1 = new CreditPayment(null, PaymentStatus.QUITADO, o1, 6);
	    o1.setPayment(pay1);
	    Payment pay2 = new TicketPayment(null, PaymentStatus.PENDENTE, o2, sdf.parse("20/10/2017 00:00"), null);
	    o2.setPayment(pay2);
	    		
	    orderRepository.saveAll(Arrays.asList(o1, o2));
	   
	    paymentRepository.saveAll(Arrays.asList(pay1, pay2));
	
	    OrderItem oi1 = new OrderItem(o1, p1, 0.0, 1, 2000.0);
	    OrderItem oi2 = new OrderItem(o1, p2, 0.0, 2, 80.0);
	    OrderItem oi3 = new OrderItem(o2, p3, 100.0, 1, 800.0);
	    
	    o1.getItens().addAll(Arrays.asList(oi1, oi2));
	    o2.getItens().addAll(Arrays.asList(oi3));
	
	    p1.getItens().addAll(Arrays.asList(oi1));
	    p2.getItens().addAll(Arrays.asList(oi2));
	    p3.getItens().addAll(Arrays.asList(oi3));
	    
	    orderItemRepository.saveAllAndFlush(Arrays.asList(oi1, oi2, oi3));
	    
	}

}
