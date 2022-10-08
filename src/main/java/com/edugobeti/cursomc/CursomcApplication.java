package com.edugobeti.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edugobeti.cursomc.domain.Adress;
import com.edugobeti.cursomc.domain.Category;
import com.edugobeti.cursomc.domain.City;
import com.edugobeti.cursomc.domain.Client;
import com.edugobeti.cursomc.domain.Product;
import com.edugobeti.cursomc.domain.State;
import com.edugobeti.cursomc.domain.enuns.ClientType;
import com.edugobeti.cursomc.repository.AdressRepository;
import com.edugobeti.cursomc.repository.CategoryRepository;
import com.edugobeti.cursomc.repository.CityRepository;
import com.edugobeti.cursomc.repository.ClientRepository;
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
	    
	    adressRepository.saveAllAndFlush(Arrays.asList(a1, a2));
	}

}
