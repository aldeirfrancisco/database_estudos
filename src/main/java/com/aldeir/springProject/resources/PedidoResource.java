package com.aldeir.springProject.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aldeir.springProject.domain.Categoria;
import com.aldeir.springProject.domain.Pedido;
import com.aldeir.springProject.dto.CategoriaDTO;
import com.aldeir.springProject.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido>find(@PathVariable Integer id) {
		
		Pedido obj = service.busca(id);
       return ResponseEntity.ok().body(obj) ;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody  Pedido obj){//coverte o json para objeto java, @Valid valida antes de passa o objeto 
		
	  obj = service.insert(obj);
	  URI uri = ServletUriComponentsBuilder.fromCurrentRequest()//fromcurrentrequest pega a url que foi usada para inseri
			  .path("/{id}").buildAndExpand(obj.getId()).toUri();
	   //pega o id do obj                 atribui o id do obj que foi inserido
	  return ResponseEntity.created(uri).build();
	}
}
