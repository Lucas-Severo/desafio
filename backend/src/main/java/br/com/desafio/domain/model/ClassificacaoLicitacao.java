package br.com.desafio.domain.model;

public enum ClassificacaoLicitacao {

	MENOR_PRECO(0), NOTA_PRECO(1);
	
	private int value;
	
	private ClassificacaoLicitacao(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
