package com.aldeir.springProject.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.aldeir.springProject.services.DBService;
 //essa classe so sera ativa quando o profiles estiver ativo no aplication
@Configuration
@Profile("dev")
public class DevConfig {
     @Autowired
     DBService dbService;
       @Value("${spring.jpa.hibernate.ddl-auto}")
     private String strategy;//pega o valor da estrategia no application-dev-properties
     
	@Bean
	public Boolean instantiateDataBase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
        dbService.instantiateTestDatabase();
		return true;
	}
}
