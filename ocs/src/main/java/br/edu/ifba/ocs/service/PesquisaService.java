package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import br.edu.ifba.ocs.repository.PesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PesquisaService {

    @Autowired
    private PesquisaRepository repository;

    public List<Pesquisa> listarTodas() {
        return repository.findAll();
    }

    public List<Pesquisa> listarPorStatus(Status status) {
        return repository.findByStatusOrderByDataInicioDesc(status);
    }

    public Optional<Pesquisa> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public Pesquisa salvar(Pesquisa pesquisa) {

        return repository.save(pesquisa);
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Pesquisa não encontrada para exclusão.");
        }
        repository.deleteById(id);
    }


    public List<Pesquisa> listarPorConta(Conta conta) {
        return repository.findByConta(conta);
    }
}
