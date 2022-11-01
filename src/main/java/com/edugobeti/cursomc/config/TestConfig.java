package com.edugobeti.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.edugobeti.cursomc.service.DataBaseService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	DataBaseService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dbService.instantiateDataBase();
		return true;
	}
}
