package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Categoria;
import br.edu.ifba.ocs.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> listar() {
        return repo.findAll();
    }

    public Optional<Categoria> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        return repo.save(categoria);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }
}
