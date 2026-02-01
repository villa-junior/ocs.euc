package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Autora;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoraRepository extends JpaRepository<Autora, UUID> {

	List<Autora> findByNomeContainingIgnoreCase(String nome);


	Page<Autora> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
