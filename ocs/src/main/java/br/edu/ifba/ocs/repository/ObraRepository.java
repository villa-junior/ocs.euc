package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
}
