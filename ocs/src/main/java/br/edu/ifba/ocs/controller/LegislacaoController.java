package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Legislacao;
import br.edu.ifba.ocs.service.LegislacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legislacoes")
public class LegislacaoController {

    @Autowired
    private LegislacaoService service;

    @GetMapping
    public List<Legislacao> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Legislacao> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Legislacao criar(@RequestBody Legislacao legislacao) {
        return service.salvar(legislacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Legislacao> atualizar(@PathVariable Integer id, @RequestBody Legislacao novaLegislacao) {
        return service.buscarPorId(id)
                .map(leg -> {
                    leg.setNumero(novaLegislacao.getNumero());
                    leg.setDescricao(novaLegislacao.getDescricao());
                    leg.setUrlArquivo(novaLegislacao.getUrlArquivo());
                    leg.setData(novaLegislacao.getData());
                    return ResponseEntity.ok(service.salvar(leg));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
