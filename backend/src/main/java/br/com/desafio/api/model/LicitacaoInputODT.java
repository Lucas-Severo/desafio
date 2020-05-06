package br.com.desafio.api.model;

import javax.validation.constraints.NotNull;

import br.com.desafio.domain.model.ClassificacaoLicitacao;

public class LicitacaoInputODT {

	@NotNull
	private Long id;
	
	private String descricao;
	
	private ClassificacaoLicitacao classificacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ClassificacaoLicitacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(ClassificacaoLicitacao classificacao) {
		this.classificacao = classificacao;
	}
	
	
}
