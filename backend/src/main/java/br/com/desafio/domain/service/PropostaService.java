package br.com.desafio.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.model.Proposta;
import br.com.desafio.domain.repository.PropostaRepository;

@Service
public class PropostaService {

	@Autowired
	private PropostaRepository propostaRepository;
	
	public Proposta incluir(Proposta proposta) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime date = LocalDateTime.now();
		proposta.setDataCadastro(formatter.format(date));
		return propostaRepository.save(proposta);
	}
	
	public Proposta atualizar(Proposta proposta, Long propostaId) {
		Proposta propostaExists = propostaRepository.findById(propostaId)
				.orElseThrow(() -> new RuntimeException("Proposta nao encontrada"));
		
		proposta.setId(propostaId);
		proposta.setDataCadastro(propostaExists.getDataCadastro());
		return propostaRepository.save(proposta);
	}
	
	public void excluir(Long propostaId) {
		Proposta propostaExists = propostaRepository.findById(propostaId)
				.orElseThrow(() -> new RuntimeException("Proposta nao encontrada"));
		propostaRepository.delete(propostaExists);
	}
}
