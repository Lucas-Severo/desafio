package br.com.desafio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.domain.model.Proposta;
import br.com.desafio.domain.repository.PropostaRepository;
import br.com.desafio.domain.service.PropostaService;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private PropostaService propostaService;
	
	@GetMapping
	public List<Proposta> listar() {
		return propostaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Proposta> incluir(@RequestBody Proposta proposta) {
		return ResponseEntity.ok(propostaService.incluir(proposta));
	}
	
}
