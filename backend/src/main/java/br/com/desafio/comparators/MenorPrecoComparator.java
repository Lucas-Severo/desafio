package br.com.desafio.comparators;

import java.util.Comparator;

import br.com.desafio.domain.model.Proposta;

public class MenorPrecoComparator implements Comparator<Proposta> {
	
	public int compare(Proposta p1, Proposta p2) {	
		if(p1.getPreco().compareTo(p2.getPreco()) < 0)
			return -1;
		
		else if(p1.getDataCadastro().compareTo(p2.getDataCadastro()) < 0)
			return -1;
		
		if(p1.getPreco().compareTo(p2.getPreco()) > 0)
			return 1;
		
		else if(p1.getDataCadastro().compareTo(p2.getDataCadastro()) > 0)
			return 1;
		
		return 0;
	}
}