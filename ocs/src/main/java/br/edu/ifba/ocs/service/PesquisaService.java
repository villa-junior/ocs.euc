package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.repository.PesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PesquisaService {

    @Autowired
    private PesquisaRepository repo;

    public List<Pesquisa> listar() {
        return repo.findAll();
    }

    public Optional<Pesquisa> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Pesquisa salvar(Pesquisa pesquisa) {
        return repo.save(pesquisa);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }
}
