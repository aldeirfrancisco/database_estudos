package com.aldeir.springProject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeir.springProject.domain.Cliente;
import com.aldeir.springProject.repositories.ClienteRepository;

@Service
public class ClienteService {
    
	@Autowired
	private ClienteRepository repro;
	
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repro.findById(id);
		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ",  Tipo: " + Cliente.class.getName()));
	}
}
