package br.com.desafio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.domain.repository.LicitacaoRepository;

@RestController
@RequestMapping("/teste")
public class LicitacaoController {

	@Autowired
	private LicitacaoRepository licitacaoRepository;
	
	@GetMapping
	public String teste() {
		return "Teste";
	}
	
}
