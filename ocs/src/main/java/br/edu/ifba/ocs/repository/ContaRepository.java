package br.edu.ifba.ocs.repository;

import br.edu.ifba.ocs.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    // Aqui você pode criar consultas personalizadas, por exemplo:
    Conta findByEmail(String email);
}
