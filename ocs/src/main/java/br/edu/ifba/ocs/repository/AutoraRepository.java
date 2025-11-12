package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Autora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoraRepository extends JpaRepository<Autora, Integer> {
}
