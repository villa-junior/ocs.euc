package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Autora;
import br.edu.ifba.ocs.repository.AutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoraService {

    @Autowired
    private AutoraRepository repository;

    public List<Autora> listar() {
        return repository.findAll();
    }

    public Optional<Autora> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Autora salvar(Autora autora) {
        return repository.save(autora);
    }

    public boolean deletar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
