package com.aldeir.springProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aldeir.springProject.domain.Categoria;
import com.aldeir.springProject.domain.Pedido;
import com.aldeir.springProject.domain.Produto;
import com.aldeir.springProject.repositories.CategoriaRepository;
import com.aldeir.springProject.repositories.ProdutoRepository;

@Service
public class ProdutoService {
    
	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository  categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ",  Tipo: " + Pedido.class.getName()));
	}
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
}
