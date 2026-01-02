package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Obra;
import br.edu.ifba.ocs.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/obras")
public class ObraController {

    @Autowired
    private ObraService service;


    @GetMapping
    public List<Obra> listar() {
        return service.listar();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Obra> buscar(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Obra criar(@RequestBody Obra obra) {
        return service.salvar(obra);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Obra> atualizar(
            @PathVariable UUID id,
            @RequestBody Obra novaObra) {

        return service.buscarPorId(id)
                .map(obra -> {
                    obra.setTitulo(novaObra.getTitulo());
                    obra.setResumo(novaObra.getResumo());
                    obra.setPalavrasChave(novaObra.getPalavrasChave());
                    obra.setAnoPublicacao(novaObra.getAnoPublicacao());
                    obra.setUrlArquivo(novaObra.getUrlArquivo());
                    obra.setCategoria(novaObra.getCategoria());
                    return ResponseEntity.ok(service.salvar(obra));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
