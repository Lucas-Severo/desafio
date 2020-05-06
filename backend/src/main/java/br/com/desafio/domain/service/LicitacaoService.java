package br.com.desafio.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.model.Licitacao;
import br.com.desafio.domain.repository.LicitacaoRepository;

@Service
public class LicitacaoService {

	@Autowired
	private LicitacaoRepository licitacaoRepository;
	
	public Licitacao incluir(Licitacao licitacao) {
		return licitacaoRepository.save(licitacao);
	}
	
	public void excluir(Long licitacaoId) {
		Optional<Licitacao> licitacao = licitacaoRepository.findById(licitacaoId);
		
		if(licitacao.isPresent()) {
			licitacaoRepository.deleteById(licitacaoId);
		}
	}
	
	public Licitacao atualizar(Licitacao licitacao, Long licitacaoId) {
		Optional<Licitacao> licitacaoExists = licitacaoRepository.findById(licitacaoId);
		
		if(licitacaoExists.isPresent()) {
			licitacao.setId(licitacaoId);
			return licitacaoRepository.save(licitacao);
		}
		throw new RuntimeException("Licitacao nao existe");
	}
	
}
