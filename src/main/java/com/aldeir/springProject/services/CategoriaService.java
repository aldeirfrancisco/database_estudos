package com.aldeir.springProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.aldeir.springProject.domain.Categoria;
import com.aldeir.springProject.repositories.CategoriaRepository;
import com.aldeir.springProject.resources.exception.DataIntegrityException;

@Service
public class CategoriaService {
    
	@Autowired
	private CategoriaRepository repo;
	
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ",  Tipo: " + Categoria.class.getName()));
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);//objeto novo a ser persistido não devera existir no banco
		return repo.save(obj);//si o id tiver um valor vai ser considerado uma atualizacao
	}
	public Categoria update(Categoria obj) {
		find(obj.getId());
   return repo.save(obj);
	}
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
			
		}catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir uma categoria que possui produto");
		}
	}
	public List<Categoria> findAll() {
	
		return repo.findAll();
	}
}
