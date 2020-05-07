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
	
}
