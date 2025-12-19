package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return service.listarPorStatus("ANDAMENTO");
    }


    @GetMapping("/concluida")
    public List<Pesquisa> listarConcluida() {
        return service.listarPorStatus("CONCLUIDA");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pesquisa> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Pesquisa criar(@RequestBody Pesquisa pesquisa) {
        return service.salvar(pesquisa);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pesquisa> atualizar(
            @PathVariable Integer id,
            @RequestBody Pesquisa novaPesquisa) {

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

                    return ResponseEntity.ok(service.salvar(pesquisa));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 DELETAR PESQUISA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
