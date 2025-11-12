package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Legislacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegislacaoRepository extends JpaRepository<Legislacao, Integer> {
}
