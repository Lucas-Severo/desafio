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
		Licitacao licitacaoExists = licitacaoRepository.findById(licitacaoId)
											.orElseThrow(() -> new RuntimeException("Licitacao nao encontrada"));
		
		if(licitacao.getClassificacao() != null)
			licitacaoExists.setClassificacao(licitacao.getClassificacao());
		if(licitacao.getDescricao() != null)
			licitacaoExists.setDescricao(licitacao.getDescricao());
		
		return licitacaoRepository.save(licitacaoExists);
	}
	
}
