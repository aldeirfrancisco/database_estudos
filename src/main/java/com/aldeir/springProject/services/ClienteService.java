package com.aldeir.springProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aldeir.springProject.domain.Cliente;
import com.aldeir.springProject.dto.ClienteDTO;
import com.aldeir.springProject.repositories.ClienteRepository;

@Service
public class ClienteService {
    
	@Autowired
	private ClienteRepository repo;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ",  Tipo: " + Cliente.class.getName()));
	}
	public Cliente update(Cliente obj) {
	Cliente newObj =	find(obj.getId());
	updateData(newObj, obj);
   return repo.save(obj);
	}
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
			
		}catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir porque há entidades relacionadas");
		}
		}
	public List<Cliente> findAll() {
	
		return repo.findAll();
	}
	//spring data (class page) encapsula informaçoes e operações sobre paginação
	public Page<Cliente>findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
 //page conta paginas,  linesPerPage quantas linha vai quere ,orderBy qual atributo vai ordenar, direction qual dirreção vai que ordenar acendente ou decedente
	//PageRequest objeto que vai prepara as informações para fazer a consulta que retorna a pagina de dados
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		}
		
		public Cliente fromDTO(ClienteDTO objDTO) {
	      return new Cliente(objDTO.getId(),objDTO.getNome() , objDTO.getEmail(), null,null);
		}
		private  void updateData(Cliente newObj, Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
	
}
