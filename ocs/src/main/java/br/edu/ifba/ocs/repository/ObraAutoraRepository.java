package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.ObraAutora;
import br.edu.ifba.ocs.model.ObraAutoraId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObraAutoraRepository
        extends JpaRepository<ObraAutora, ObraAutoraId> {

    @Modifying
    @Query("DELETE FROM ObraAutora oa WHERE oa.obra.id = :idObra")
    void deleteByObraId(@Param("idObra") Integer idObra);


    @Query("""
        SELECT oa.id.idAutora
        FROM ObraAutora oa
        WHERE oa.obra.id = :idObra
    """)
    List<Integer> findAutoraIdsByObraId(@Param("idObra") Integer idObra);


    @Query("""
        SELECT a.nome
        FROM ObraAutora oa
        JOIN oa.autora a
        WHERE oa.obra.id = :idObra
    """)
    List<String> findNomesAutorasByObraId(@Param("idObra") Integer idObra);
}
