package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ObraRepository extends JpaRepository<Obra, UUID> {

    List<Obra> findAllByOrderByAnoPublicacaoDesc();
    List<Obra> findByCategoriaId(UUID idCategoria);

    List<Obra> findByCategoriaIdOrderByAnoPublicacaoDesc(UUID categoriaId);
}
