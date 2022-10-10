package com.edugobeti.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
import com.edugobeti.cursomc.repository.OrderItemRepository;
import com.edugobeti.cursomc.repository.OrderRepository;
import com.edugobeti.cursomc.repository.PaymentRepository;
import com.edugobeti.cursomc.repository.ProductRepository;
import com.edugobeti.cursomc.repository.StateRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
	OrderRepository orderRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	OrderItemRepository	orderItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		

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
