package br.com.desafio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.model.Proposta;
import br.com.desafio.domain.repository.PropostaRepository;

@Service
public class PropostaService {

	@Autowired
	private PropostaRepository propostaRepository;
	
	public Proposta incluir(Proposta proposta) {
		return propostaRepository.save(proposta);
	}
	
	public Proposta atualizar(Proposta proposta, Long propostaId) {
		Proposta propostaExists = propostaRepository.findById(propostaId)
				.orElseThrow(() -> new RuntimeException("Proposta nao encontrada"));
		
		proposta.setId(propostaId);
		return propostaRepository.save(proposta);
	}
	
	public void excluir(Long propostaId) {
		Proposta propostaExists = propostaRepository.findById(propostaId)
				.orElseThrow(() -> new RuntimeException("Proposta nao encontrada"));
		propostaRepository.delete(propostaExists);
	}
}
