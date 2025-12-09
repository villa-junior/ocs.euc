package br.edu.ifba.ocs.service;


import br.edu.ifba.ocs.model.Autora;
import br.edu.ifba.ocs.repository.AutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class AutoraService {


    @Autowired
    private AutoraRepository repo;


    public List<Autora> listar() {
        return repo.findAll();
    }


    public Optional<Autora> buscarPorId(Integer id) {
        return repo.findById(id);
    }


    public Autora salvar(Autora autora) {
        return repo.save(autora);
    }


    public void deletar(Integer id) {
        repo.deleteById(id);
    }
}