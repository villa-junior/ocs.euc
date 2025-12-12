package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Legislacao;
import br.edu.ifba.ocs.repository.LegislacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegislacaoService {

    @Autowired
    private LegislacaoRepository repo;

    public List<Legislacao> listar() {
        return repo.findAll();
    }

    public Optional<Legislacao> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Legislacao salvar(Legislacao legislacao) {
        return repo.save(legislacao);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }
}
