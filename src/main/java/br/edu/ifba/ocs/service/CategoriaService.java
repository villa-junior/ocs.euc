package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Categoria;
import br.edu.ifba.ocs.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Optional<Categoria> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}
