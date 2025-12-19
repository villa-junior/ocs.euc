package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesquisaRepository extends JpaRepository<Pesquisa, Integer> {


    List<Pesquisa> findByStatusOrderByDataInicioDesc(String status);
}
