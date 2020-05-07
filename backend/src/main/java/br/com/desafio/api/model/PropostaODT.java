package br.com.desafio.api.model;

import java.math.BigDecimal;

public class PropostaODT {

	private Long id;
	
	private String fornecedor;
	
	private BigDecimal preco;
	
	private String dataCadastro;
	
	private Long licitacaoId;
	
	private String licitacaoClassificacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getLicitacaoId() {
		return licitacaoId;
	}

	public void setLicitacaoId(Long licitacaoId) {
		this.licitacaoId = licitacaoId;
	}

	public String getLicitacaoClassificacao() {
		return licitacaoClassificacao;
	}

	public void setLicitacaoClassificacao(String licitacaoClassificacao) {
		this.licitacaoClassificacao = licitacaoClassificacao;
	}
	
	
}
