package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Obra;
import br.edu.ifba.ocs.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraService {

    @Autowired
    private ObraRepository repo;

    public List<Obra> listar() {
        return repo.findAll();
    }

    public Optional<Obra> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Obra salvar(Obra obra) {
        return repo.save(obra);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }
}
