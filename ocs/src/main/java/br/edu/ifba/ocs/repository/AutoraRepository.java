package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Autora;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoraRepository extends JpaRepository<Autora, Integer> {

	List<Autora> findByNomeContainingIgnoreCase(String nome);
}
