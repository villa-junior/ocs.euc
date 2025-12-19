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
    private PesquisaRepository repository;

    // 🔹 LISTAR TODAS
    public List<Pesquisa> listarTodas() {
        return repository.findAll();
    }

    // 🔹 LISTAR POR STATUS
    public List<Pesquisa> listarPorStatus(String status) {
        return repository.findByStatusOrderByDataInicioDesc(status);
    }

    public Optional<Pesquisa> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Pesquisa salvar(Pesquisa pesquisa) {
        return repository.save(pesquisa);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
