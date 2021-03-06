package com.aldeir.springProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aldeir.springProject.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
//objeto desse tipo e capaz de fazer operacoes de acesso a dados referentes a objeto Estado
}
