package com.aldeir.springProject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aldeir.springProject.domain.Categoria;
import com.aldeir.springProject.domain.Cidade;
import com.aldeir.springProject.domain.Cliente;
import com.aldeir.springProject.domain.Endereco;
import com.aldeir.springProject.domain.Estado;
import com.aldeir.springProject.domain.EstadoPagamento;
import com.aldeir.springProject.domain.ItemPedido;
import com.aldeir.springProject.domain.Pagamento;
import com.aldeir.springProject.domain.PagamentoComBoleto;
import com.aldeir.springProject.domain.PagamentoComCartao;
import com.aldeir.springProject.domain.Pedido;
import com.aldeir.springProject.domain.Produto;
import com.aldeir.springProject.domain.enums.TipoCliente;
import com.aldeir.springProject.repositories.CategoriaRepository;
import com.aldeir.springProject.repositories.CidadeRepository;
import com.aldeir.springProject.repositories.ClienteRepository;
import com.aldeir.springProject.repositories.EnderecoRepository;
import com.aldeir.springProject.repositories.EstadoRepository;
import com.aldeir.springProject.repositories.ItemPedidoRepository;
import com.aldeir.springProject.repositories.PagamentoRepository;
import com.aldeir.springProject.repositories.PedidoRepository;
import com.aldeir.springProject.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner {
     
	@Autowired
    private CategoriaRepository categoriaRepository;
	
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired 
    private EstadoRepository  estadoRepository;
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository; 
    
    @Autowired
    PedidoRepository pedidoRepository;
    
    @Autowired
    PagamentoRepository    pagamentoRepository; 
    
    @Autowired
    ItemPedidoRepository  itemPedidoRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

	public  void run(String... args) throws Exception {
		Categoria cat = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		
		Produto p2 = new Produto(null,"Impressora", 800.00);
		
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		p1.getCategorias().addAll(Arrays.asList(cat));
		p2.getCategorias().addAll(Arrays.asList(cat,cat2));//associando categoria ao produto
		p3.getCategorias().addAll(Arrays.asList(cat));
		
		cat.getProdutos().addAll(Arrays.asList(p1,p2,p3)); //associando produtos ao categoria
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		categoriaRepository.saveAll(Arrays.asList(cat,cat2)); //salvando as categorias
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));//salvando os produtos
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1= new Cidade(null, "Uberlândia", est1); //associação entre cidade e estado
		Cidade c2= new Cidade(null, "São Paulo", est2);
		Cidade c3= new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));//associação  entre estado e cidade
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 = new Cliente(null,"Maria Silva" ,"maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefone().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303","Jardim","38220834",cli1, c1);
		Endereco e2 =new Endereco(null, "AvenidaMatos","105","Sala 800","Centro","38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1,e1);
	    Pedido ped2 = new Pedido(null, sdf.parse("25/07/2020 21:37"), cli1,e2);
	    
	    Pagamento pagt1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1,6);
	     ped1.setPagamento(pagt1);
	    
	     Pagamento pagt2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE, ped2,sdf.parse("24/07/2020 22:42"), null);
	     ped2.setPagamento(pagt2);
	     
	     cli1.getPedido().addAll(Arrays.asList(ped1,ped2));
	     
	      pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
	      pagamentoRepository.saveAll(Arrays.asList(pagt1,pagt2)); 
	      
	      ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
	      ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	      ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	      
	      ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	      ped2.getItens().addAll(Arrays.asList(ip3));
	      
	      p1.getItens().addAll(Arrays.asList(ip1));
	      p2.getItens().addAll(Arrays.asList(ip3));
	      p3.getItens().addAll(Arrays.asList(ip2));
	      
	      itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	      
			}

	
}