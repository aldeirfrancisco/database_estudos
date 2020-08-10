package com.aldeir.springProject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeir.springProject.domain.Pedido;
import com.aldeir.springProject.repositories.PedidoRepository;

@Service
public class PedidoService {
    
	@Autowired
	private PedidoRepository repro;
	
	
	public Pedido busca(Integer id) {
		Optional<Pedido> obj = repro.findById(id);
		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ",  Tipo: " + Pedido.class.getName()));
	}
}
