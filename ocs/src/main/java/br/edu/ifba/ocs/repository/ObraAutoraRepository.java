package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.ObraAutora;
import br.edu.ifba.ocs.model.ObraAutoraId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraAutoraRepository extends JpaRepository<ObraAutora, ObraAutoraId> {
}
