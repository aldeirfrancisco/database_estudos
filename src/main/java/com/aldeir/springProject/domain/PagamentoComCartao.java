package com.aldeir.springProject.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	
	 private Integer numeroDePacela;
	 
	 public PagamentoComCartao() {
		 
	 }

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDePacela) {
		super(id, estado, pedido);
		this.numeroDePacela = numeroDePacela;
	}

	public Integer getNumeroDePacela() {
		return numeroDePacela;
	}

	public void setNumeroDePacela(Integer numeroDePacela) {
		this.numeroDePacela = numeroDePacela;
	}
	 
}
