package com.aldeir.springProject.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.aldeir.springProject.services.DBService;
 //essa classe so sera ativa quando o profiles estiver ativo no aplication
@Configuration
@Profile("test")
public class TestConfig {
     @Autowired
     DBService dbService;
     
	@Bean
	public Boolean instantiateDataBase() throws ParseException {
        dbService.instantiateTestDatabase();
		return true;
	}
}
