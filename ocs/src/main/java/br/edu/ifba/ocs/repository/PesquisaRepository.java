package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PesquisaRepository extends JpaRepository<Pesquisa, UUID> {

    List<Pesquisa> findByStatusOrderByDataInicioDesc(Status status);

    List<Pesquisa> findByTituloContainingIgnoreCase(String termo);

    List<Pesquisa> findByConta(Conta conta);

    Page<Pesquisa> findByStatus(Status status, Pageable pageable);


    Page<Pesquisa> findByTituloContainingIgnoreCase(String termo, Pageable pageable);


    Page<Pesquisa> findByConta(Conta conta, Pageable pageable);
}
