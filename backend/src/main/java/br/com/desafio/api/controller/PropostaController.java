package br.com.desafio.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.api.model.PropostaODT;
import br.com.desafio.domain.model.Licitacao;
import br.com.desafio.domain.model.Proposta;
import br.com.desafio.domain.repository.PropostaRepository;
import br.com.desafio.domain.service.PropostaService;

@RestController
@CrossOrigin
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private PropostaService propostaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<PropostaODT> listar() {
		return toCollectionModel(propostaRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PropostaODT> incluir(@RequestBody PropostaODT propostaODT) {
		Proposta proposta = toEntity(propostaODT);
		return ResponseEntity.ok(toModel(propostaService.incluir(proposta)));
	}
	
	@PutMapping("/{propostaId}")
	public ResponseEntity<PropostaODT> atualizar(@PathVariable Long propostaId,
			@RequestBody PropostaODT propostaODT) {
		Proposta proposta = toEntity(propostaODT);
		return ResponseEntity.ok(toModel(propostaService.atualizar(proposta, propostaId)));
	}
	
	@DeleteMapping("/{propostaId}")
	public ResponseEntity<Void> excluir(@PathVariable Long propostaId) {
		propostaService.excluir(propostaId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/classificacao")
	public List<PropostaODT> classificar(@RequestBody Licitacao licitacao) {
		return toCollectionModel(propostaService.classificacao(licitacao));
	}
	
	private List<PropostaODT> toCollectionModel(List<Proposta> proposta) {
		return proposta.stream()
				.map(p -> toModel(p))
				.collect(Collectors.toList());
	}
	
	private PropostaODT toModel(Proposta proposta) {
		return modelMapper.map(proposta, PropostaODT.class);
	}
	
	private Proposta toEntity(PropostaODT proposta) {
		return modelMapper.map(proposta, Proposta.class);
	}
}
