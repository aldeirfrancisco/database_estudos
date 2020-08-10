package com.aldeir.springProject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeir.springProject.domain.Categoria;
import com.aldeir.springProject.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    
	@Autowired
	private CategoriaRepository repro;
	
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repro.findById(id);
		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ",  Tipo: " + Categoria.class.getName()));
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);//objeto novo a ser persistido não devera existir no banco
		return repro.save(obj);//si o id tiver um valor vai ser considerado uma atualizacao
	}
}
