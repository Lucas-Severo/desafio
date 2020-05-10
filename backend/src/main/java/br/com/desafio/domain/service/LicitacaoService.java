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
	
	public Licitacao findById(Long id) {
		return licitacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Licitacao nao encontrada"));
	}
	
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
		licitacaoRepository.findById(licitacaoId).orElseThrow(() -> new RuntimeException("Licitacao nao encontrada"));
		
		licitacao.setId(licitacaoId);
		
		return licitacaoRepository.save(licitacao);
	}
	
}
