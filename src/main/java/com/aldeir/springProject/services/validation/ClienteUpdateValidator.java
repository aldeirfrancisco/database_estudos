package com.aldeir.springProject.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.aldeir.springProject.domain.Cliente;
import com.aldeir.springProject.dto.ClienteDTO;
import com.aldeir.springProject.repositories.ClienteRepository;
import com.aldeir.springProject.resources.exception.FieldMessage;

public class ClienteUpdateValidator  implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
      @Autowired
      private  HttpServletRequest request; //tem uma função que permite opter o parametro da uri no caso aqui o id
	  @Autowired
	  private ClienteRepository repo;
	 @Override
	 public void initialize(ClienteUpdate ann) {
		 
	 }
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
	//map estrutura de dados (Colecao), em uma requisicao possui varios atributos que sao armazenado em um Map parecido com json
		                                              //    (HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE identifica os atributos             
		@SuppressWarnings("unchecked")
		Map<String, String> map =(Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));//pegou o id
		
		
		List<FieldMessage> list = new ArrayList<>();
		
		  Cliente aux = repo.findByEmail(objDto.getEmail());
		  
		   if(aux != null && !aux.getId().equals(uriId)) {
			   list.add(new FieldMessage("Email"," Email já existente"));
		   }
		 for(FieldMessage e : list) {
			 context.disableDefaultConstraintViolation();
			 context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
			  .addConstraintViolation();
		 }
		return list.isEmpty();
	}

}
