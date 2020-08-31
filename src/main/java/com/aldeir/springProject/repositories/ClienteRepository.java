package com.aldeir.springProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aldeir.springProject.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
//objeto desse tipo e capaz de fazer operacoes de acesso a dados referentes a objeto Categoria
     
	@Transactional(readOnly = true)//ele nao precissa ser envolvida com uma transacao de banco de dados 
	Cliente findByEmail(String email); //spring data jpa implementa essa busca que foi criada
}
