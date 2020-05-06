package br.com.desafio.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@GetMapping
	public String teste() {
		return "teste";
	}
	
}
