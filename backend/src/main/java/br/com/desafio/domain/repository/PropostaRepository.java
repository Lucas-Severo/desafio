package br.com.desafio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.domain.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

}
