package com.aldeir.springProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aldeir.springProject.domain.Categoria;
import com.aldeir.springProject.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
//objeto desse tipo e capaz de fazer operacoes de acesso a dados referentes a objeto Categoria
}
