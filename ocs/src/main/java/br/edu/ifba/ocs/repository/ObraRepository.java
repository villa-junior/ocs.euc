package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ObraRepository extends JpaRepository<Obra, UUID> {

    List<Obra> findAllByOrderByAnoPublicacaoDesc();
    List<Obra> findByCategoriaId(UUID idCategoria);

    List<Obra> findByCategoriaIdOrderByAnoPublicacaoDesc(UUID categoriaId);
    Page<Obra> findAll(Pageable pageable);


    Page<Obra> findByCategoriaId(UUID categoriaId, Pageable pageable);

}
