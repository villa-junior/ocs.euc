package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import br.edu.ifba.ocs.repository.PesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Pesquisa> listarPorStatus(Status status, Pageable pageable) {
        return repository.findByStatus(status, pageable);
    }
    public Optional<Pesquisa> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public Pesquisa salvar(Pesquisa pesquisa) {
        return repository.save(pesquisa);
    }

    public void deletar(UUID id, Conta contaLogada) {

        Pesquisa pesquisa = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pesquisa não encontrada"));


        if (pesquisa.getConta() == null) {
            if (contaLogada.getPerfil() != br.edu.ifba.ocs.model.Perfil.admin) {
                throw new SecurityException(
                        "Você não tem permissão para excluir esta pesquisa"
                );
            }
            repository.delete(pesquisa);
            return;
        }


        if (contaLogada.getPerfil() == br.edu.ifba.ocs.model.Perfil.admin) {
            repository.delete(pesquisa);
            return;
        }


        if (!pesquisa.getConta().getId().equals(contaLogada.getId())) {
            throw new SecurityException(
                    "Você não tem permissão para excluir esta pesquisa"
            );
        }

        repository.delete(pesquisa);
    }


    public Pesquisa editar(UUID id, Pesquisa dados, Conta contaLogada) {
        Pesquisa pesquisa = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pesquisa não encontrada"));

        if (!pesquisa.getConta().getId().equals(contaLogada.getId())) {
            throw new SecurityException("Você não tem permissão para editar esta pesquisa");
        }

        pesquisa.setTitulo(dados.getTitulo());
        pesquisa.setDescricao(dados.getDescricao());
        pesquisa.setStatus(dados.getStatus());
        pesquisa.setDataInicio(dados.getDataInicio());
        pesquisa.setDataFim(dados.getDataFim());
        pesquisa.setUrlParticipante(dados.getUrlParticipante());
        pesquisa.setUrlOrganizador(dados.getUrlOrganizador());
        pesquisa.setArquivoResultados(dados.getArquivoResultados());

        return repository.save(pesquisa);
    }

    public List<Pesquisa> listarPorConta(Conta conta) {
        return repository.findByConta(conta);
    }
}
