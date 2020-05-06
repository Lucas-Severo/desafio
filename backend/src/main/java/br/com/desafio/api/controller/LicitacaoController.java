package br.com.desafio.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.api.model.LicitacaoODT;
import br.com.desafio.domain.model.Licitacao;
import br.com.desafio.domain.repository.LicitacaoRepository;
import br.com.desafio.domain.service.LicitacaoService;

@RestController
@RequestMapping("/licitacao")
public class LicitacaoController {

	@Autowired
	private LicitacaoRepository licitacaoRepository;
	
	@Autowired
	private LicitacaoService licitacaoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<LicitacaoODT> listar() {
		return toCollectionModel(licitacaoRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LicitacaoODT incluir(@Valid @RequestBody Licitacao licitacao) {
		return toModel(licitacaoService.incluir(licitacao));
	}
	
	@DeleteMapping("/{licitacaoId}")
	public ResponseEntity<LicitacaoODT> excluir(@Valid @PathVariable Long licitacaoId) {
		if(!licitacaoRepository.findById(licitacaoId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		licitacaoService.excluir(licitacaoId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{licitacaoId}")
	public ResponseEntity<LicitacaoODT> atualizar(@Valid @PathVariable Long licitacaoId,
											   @RequestBody Licitacao licitacao) {
		if(!licitacaoRepository.findById(licitacaoId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		licitacao = licitacaoService.atualizar(licitacao, licitacaoId);
		return ResponseEntity.ok(toModel(licitacao));
	}
	
	private List<LicitacaoODT> toCollectionModel(List<Licitacao> licitacoes) {
		return licitacoes.stream()
				.map(licitacao -> toModel(licitacao))
				.collect(Collectors.toList());
	}
	
	private LicitacaoODT toModel(Licitacao licitacao ) {
		return modelMapper.map(licitacao, LicitacaoODT.class);
	}
	
}
