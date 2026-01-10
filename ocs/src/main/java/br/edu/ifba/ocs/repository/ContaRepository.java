package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByEmail(String email);
    List<Conta> findByPerfilAndValidadoFalse(Perfil perfil);
}
