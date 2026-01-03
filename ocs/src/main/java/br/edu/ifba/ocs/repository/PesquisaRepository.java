package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PesquisaRepository extends JpaRepository<Pesquisa, UUID> {

    List<Pesquisa> findByStatusOrderByDataInicioDesc(Status status);

    List<Pesquisa> findByTituloContainingIgnoreCase(String termo);

    List<Pesquisa> findByConta(Conta conta);
}
