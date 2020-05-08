package br.com.desafio.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.comparators.MenorPrecoComparator;
import br.com.desafio.comparators.NotaPrecoComparator;
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
	
	public List<Proposta> classificacao(int tipo) {
		List<Proposta> propostas = propostaRepository.findAll();
		List<Proposta> filtradas = propostas.stream()
				.filter((proposta) -> {
					return proposta.getLicitacao().getClassificacao().getValue() == tipo;
				})
				.collect(Collectors.toList());
		if(tipo == 0)
			filtradas.sort(new MenorPrecoComparator());
		if(tipo == 1)
			filtradas.sort(new NotaPrecoComparator());
		return filtradas;
	}
}
