package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {
    Conta findByEmail(String email);
}

