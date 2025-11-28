package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    Conta findByEmail(String email);
}

