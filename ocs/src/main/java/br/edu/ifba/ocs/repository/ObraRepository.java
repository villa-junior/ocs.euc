package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObraRepository extends JpaRepository<Obra, Integer> {

    List<Obra> findAllByOrderByAnoPublicacaoDesc();
    List<Obra> findByCategoriaId(Integer idCategoria);

    List<Obra> findByCategoriaIdOrderByAnoPublicacaoDesc(Integer categoriaId);
}
