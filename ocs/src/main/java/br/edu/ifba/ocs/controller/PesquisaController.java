package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import br.edu.ifba.ocs.service.PesquisaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pesquisas")
public class PesquisaController {

    @Autowired
    private PesquisaService service;

    @GetMapping
    public List<Pesquisa> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/andamento")
    public List<Pesquisa> listarAndamento() {
        return service.listarPorStatus(Status.EM_ANDAMENTO);
    }

    @GetMapping("/concluida")
    public List<Pesquisa> listarConcluida() {
        return service.listarPorStatus(Status.CONCLUIDA);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pesquisa> buscar(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Pesquisa pesquisa) {
        try {
            Pesquisa salva = service.salvar(pesquisa);
            return ResponseEntity.ok(salva);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody Pesquisa novaPesquisa) {

        return service.buscarPorId(id)
                .map(pesquisa -> {
                    pesquisa.setTitulo(novaPesquisa.getTitulo());
                    pesquisa.setDescricao(novaPesquisa.getDescricao());
                    pesquisa.setStatus(novaPesquisa.getStatus());
                    pesquisa.setDataInicio(novaPesquisa.getDataInicio());
                    pesquisa.setDataFim(novaPesquisa.getDataFim());
                    pesquisa.setUrlParticipante(novaPesquisa.getUrlParticipante());
                    pesquisa.setUrlOrganizador(novaPesquisa.getUrlOrganizador());
                    pesquisa.setArquivoResultados(novaPesquisa.getArquivoResultados());

                    try {
                        Pesquisa atualizada = service.salvar(pesquisa);
                        return ResponseEntity.ok(atualizada);
                    } catch (IllegalArgumentException ex) {
                        return ResponseEntity.badRequest().body(ex.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id) {
        if (service.buscarPorId(id).isPresent()) {
            try {
                service.deletar(id);
                return ResponseEntity.noContent().build();
            } catch (IllegalArgumentException ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }
}