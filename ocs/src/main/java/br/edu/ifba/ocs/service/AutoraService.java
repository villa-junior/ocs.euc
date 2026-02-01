package br.edu.ifba.ocs.service;

import br.edu.ifba.ocs.model.Autora;
import br.edu.ifba.ocs.repository.AutoraRepository;
import br.edu.ifba.ocs.repository.ObraAutoraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutoraService {

    @Autowired
    private AutoraRepository repository;

    @Autowired
    private ObraAutoraRepository obraAutoraRepository;

    public List<Autora> listar() {
        return repository.findAll();
    }
    public Page<Autora> listarPaginado(String termo, Pageable pageable) {
        if (termo != null && !termo.isBlank()) {
            return repository.findByNomeContainingIgnoreCase(termo, pageable);
        }
        return repository.findAll(pageable);
    }
    public Page<Autora> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Autora> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public Autora salvar(Autora autora) {
        return repository.save(autora);
    }

    @Transactional
    public void deletar(UUID id) {

        Autora autora = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autora não encontrada"));


        if (obraAutoraRepository.existsByAutora_Id(id)) {
            throw new RuntimeException(
                    "Não é possível excluir a autora pois ela está vinculada a obras"
            );
        }

        repository.delete(autora);
    }
}
