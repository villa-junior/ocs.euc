package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesquisaRepository extends JpaRepository<Pesquisa, Integer> {
}
