package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repo;

    public List<Conta> listar() {
        return repo.findAll();
    }

    public Optional<Conta> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Conta salvar(Conta conta) {
        return repo.save(conta);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }
}
