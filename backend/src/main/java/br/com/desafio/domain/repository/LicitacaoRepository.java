package br.com.desafio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.domain.model.Licitacao;

@Repository
public interface LicitacaoRepository extends JpaRepository<Licitacao, Long>{

}
