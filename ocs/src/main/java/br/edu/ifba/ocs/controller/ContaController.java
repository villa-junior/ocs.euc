package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Conta;
import br.edu.ifba.ocs.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @GetMapping
    public List<Conta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Conta criar(@RequestBody Conta conta) {
        return service.salvar(conta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Integer id, @RequestBody Conta novaConta) {
        return service.buscarPorId(id)
                .map(conta -> {
                    conta.setNome(novaConta.getNome());
                    conta.setEmail(novaConta.getEmail());
                    conta.setSenhaHash(novaConta.getSenhaHash());
                    conta.setPerfil(novaConta.getPerfil());
                    conta.setInstituicao(novaConta.getInstituicao());
                    return ResponseEntity.ok(service.salvar(conta));
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
