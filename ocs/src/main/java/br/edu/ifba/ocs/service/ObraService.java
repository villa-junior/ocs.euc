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
    private ObraRepository repository;

    // LISTAR TODAS (usado pela API REST)
    public List<Obra> listar() {
        return repository.findAll();
    }

    // LISTAR ORDENADO (usado pelo Thymeleaf)
    public List<Obra> listarOrdenadoPorAnoDesc() {
        return repository.findAllByOrderByAnoPublicacaoDesc();
    }

    public Optional<Obra> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Obra salvar(Obra obra) {
        return repository.save(obra);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
    public List<Obra> listarPorCategoria(Integer categoriaId) {
        return repository.findByCategoriaIdOrderByAnoPublicacaoDesc(categoriaId);
    }

}
