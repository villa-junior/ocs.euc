package br.edu.ifba.ocs.controller;


import br.edu.ifba.ocs.model.Autora;
import br.edu.ifba.ocs.service.AutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/autoras")
public class AutoraController {


    @Autowired
    private AutoraService service;


    @GetMapping
    public List<Autora> listar() {
        return service.listar();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Autora> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Autora criar(@RequestBody Autora autora) {
        return service.salvar(autora);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Autora> atualizar(@PathVariable Integer id, @RequestBody Autora novaAutora) {
        return service.buscarPorId(id)
                .map(autora -> {
                    autora.setNome(novaAutora.getNome());
                    autora.setEmail(novaAutora.getEmail());
                    autora.setInstituicao(novaAutora.getInstituicao());
                    return ResponseEntity.ok(service.salvar(autora));
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