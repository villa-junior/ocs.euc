package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.ObraAutora;
import br.edu.ifba.ocs.model.ObraAutoraId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ObraAutoraRepository
        extends JpaRepository<ObraAutora, ObraAutoraId> {

    @Modifying
    @Query("DELETE FROM ObraAutora oa WHERE oa.obra.id = :idObra")
    void deleteByObraId(@Param("idObra") Integer idObra);
}
