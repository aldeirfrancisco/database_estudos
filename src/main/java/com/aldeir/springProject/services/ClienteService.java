package com.aldeir.springProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aldeir.springProject.domain.Cidade;
import com.aldeir.springProject.domain.Cliente;
import com.aldeir.springProject.domain.Endereco;
import com.aldeir.springProject.domain.enums.TipoCliente;
import com.aldeir.springProject.dto.ClienteDTO;
import com.aldeir.springProject.dto.ClienteNewDTO;
import com.aldeir.springProject.repositories.CidadeRepository;
import com.aldeir.springProject.repositories.ClienteRepository;
import com.aldeir.springProject.repositories.EnderecoRepository;

@Service
public class ClienteService {
    
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	CidadeRepository	cidaderepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ",  Tipo: " + Cliente.class.getName()));
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
			throw new DataIntegrityViolationException("Não é possível excluir porque há pedidos relacionadas");
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
		public Cliente fromDTO(ClienteNewDTO objDto) {
			Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
			Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
			Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
			cli.getEnderecos().add(end);
			cli.getTelefone().add(objDto.getTelefone1());
			if (objDto.getTelefone2()!=null) {
				cli.getTelefone().add(objDto.getTelefone2());
			}
			if (objDto.getTelefone3()!=null) {
				cli.getTelefone().add(objDto.getTelefone3());
			}
			return cli;
		}
		private  void updateData(Cliente newObj, Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
	
}
