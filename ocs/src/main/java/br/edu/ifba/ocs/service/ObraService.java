package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.*;
import br.edu.ifba.ocs.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ObraService {

    @Autowired
    private ObraRepository repository;

    @Autowired
    private AutoraRepository autoraRepository;

    @Autowired
    private ObraAutoraRepository obraAutoraRepository;


    public List<Obra> listar() {
        return repository.findAll();
    }


    public List<Obra> listarOrdenadoPorAnoDesc() {
        return repository.findAllByOrderByAnoPublicacaoDesc();
    }

    public Optional<Obra> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<Obra> listarPorCategoria(Integer categoriaId) {
        return repository.findByCategoriaIdOrderByAnoPublicacaoDesc(categoriaId);
    }


    public Obra salvar(Obra obra) {
        return repository.save(obra);
    }


    @Transactional
    public Obra salvarComAutoras(Obra obra, List<Integer> autorasIds) {


        if (obra.getDataRegistro() == null) {
            obra.setDataRegistro(LocalDate.now());
        }


        Obra salva = repository.save(obra);


        obraAutoraRepository.deleteByObraId(salva.getId());


        if (autorasIds == null || autorasIds.isEmpty()) {
            return salva;
        }


        for (Integer idAutora : autorasIds) {

            Autora autora =
                    autoraRepository.findById(idAutora).orElseThrow();

            ObraAutora oa = new ObraAutora();
            oa.setObra(salva);
            oa.setAutora(autora);
            oa.setId(new ObraAutoraId(
                    salva.getId(),
                    autora.getId()
            ));

            obraAutoraRepository.save(oa);
        }

        return salva;
    }


    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
