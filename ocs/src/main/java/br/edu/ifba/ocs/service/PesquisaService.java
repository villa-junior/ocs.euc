package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import br.edu.ifba.ocs.repository.PesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Optional<Pesquisa> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Pesquisa salvar(Pesquisa pesquisa) {

        if (pesquisa.getStatus() == Status.EM_ANDAMENTO && pesquisa.getDataFim() != null) {
            throw new IllegalArgumentException("Você não pode cadastrar uma data final em uma pesquisa com status 'Em Andamento'.");
        }

        if (pesquisa.getDataInicio() != null && pesquisa.getDataFim() != null &&
                pesquisa.getDataFim().isBefore(pesquisa.getDataInicio())) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }
        return repository.save(pesquisa);
    }
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Pesquisa não encontrada para exclusão.");
        }
        repository.deleteById(id);
    }
}