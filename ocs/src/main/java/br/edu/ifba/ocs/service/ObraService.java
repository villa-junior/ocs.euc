package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.*;
import br.edu.ifba.ocs.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ObraService {

    @Autowired
    private ObraRepository repository;

    @Autowired
    private AutoraRepository autoraRepository;

    @Autowired
    private ObraAutoraRepository obraAutoraRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<Obra> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Obra> listarPorCategoriaPaginado(UUID categoriaId, Pageable pageable) {
        return repository.findByCategoriaId(categoriaId, pageable);
    }


    public List<Obra> listar() {
        return repository.findAll();
    }

    public List<Obra> listarOrdenadoPorAnoDesc() {
        return repository.findAllByOrderByAnoPublicacaoDesc();
    }

    public Optional<Obra> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public List<Obra> listarPorCategoria(UUID categoriaId) {
        return repository.findByCategoriaIdOrderByAnoPublicacaoDesc(categoriaId);
    }


    public Obra salvar(Obra obra) {
        return repository.save(obra);
    }

    @Transactional
    public Obra salvarComAutoras(
            Obra obra,
            UUID categoriaId,
            List<UUID> autorasIds
    ) {

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        obra.setCategoria(categoria);

        if (obra.getDataRegistro() == null) {
            obra.setDataRegistro(LocalDate.now());
        }

        Obra salva = repository.saveAndFlush(obra);

        obraAutoraRepository.deleteByObraId(salva.getId());

        if (autorasIds != null) {
            for (UUID idAutora : autorasIds) {

                Autora autora = autoraRepository.findById(idAutora)
                        .orElseThrow();

                ObraAutora oa = new ObraAutora();
                oa.setId(new ObraAutoraId(
                        salva.getId(),
                        autora.getId()
                ));
                oa.setObra(salva);
                oa.setAutora(autora);

                obraAutoraRepository.save(oa);
            }
        }

        return salva;
    }


    public List<UUID> buscarIdsAutoras(UUID obraId) {
        return obraAutoraRepository.findAutoraIdsByObraId(obraId);
    }


    public List<String> buscarNomesAutoras(UUID obraId) {
        return obraAutoraRepository.findNomesAutorasByObraId(obraId);
    }


    @Transactional
    public void deletar(UUID obraId) {

        Obra obra = repository.findById(obraId)
                .orElseThrow(() -> new RuntimeException("Obra não encontrada"));

        obraAutoraRepository.deleteByObraId(obraId);


        repository.delete(obra);
    }
}