package com.aldeir.springProject.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {
     
	private static final long serialVersionUID = 1L;
     
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamanto;
	 
	public PagamentoComBoleto() {
	}

	//po ser um subClass o construtor e baseado na super class
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,Date dataVencimento,Date dataPagamanto) {
		super(id, estado, pedido);
		this.dataPagamanto = dataPagamanto;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamanto() {
		return dataPagamanto;
	}

	public void setDataPagamanto(Date dataPagamanto) {
		this.dataPagamanto = dataPagamanto;
	}

	
	
}
