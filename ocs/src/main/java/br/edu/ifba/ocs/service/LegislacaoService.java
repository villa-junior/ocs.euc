package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Legislacao;
import br.edu.ifba.ocs.repository.LegislacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LegislacaoService {

    private final LegislacaoRepository repository;

    public LegislacaoService(LegislacaoRepository repository) {
        this.repository = repository;
    }

    public List<Legislacao> listarTodas() {
        return repository.findAll();
    }

    public Legislacao salvar(Legislacao legislacao) {
        return repository.save(legislacao);
    }

    public Legislacao buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Legislação não encontrada"));
    }

    public void excluir(UUID id) {
        repository.deleteById(id);
    }
}
