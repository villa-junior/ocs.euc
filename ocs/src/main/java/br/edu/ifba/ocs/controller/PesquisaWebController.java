package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import br.edu.ifba.ocs.service.PesquisaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pesquisas")
public class PesquisaWebController {

    @Autowired
    private PesquisaService service;


    @GetMapping("/{status}")
    public String listarPorStatus(@PathVariable Status status, Model model) {
        model.addAttribute("pesquisas", service.listarPorStatus(status));
        model.addAttribute("status", status);
        return "pesquisas/listar";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        model.addAttribute("pesquisa", new Pesquisa());
        model.addAttribute("hoje", java.time.LocalDate.now());
        return "pesquisas/cadastrar";
    }


    @PostMapping
    public String salvar(
            @Valid @ModelAttribute("pesquisa") Pesquisa pesquisa,
            BindingResult result,
            Model model
    ) {

        if (pesquisa.getStatus() == Status.EM_ANDAMENTO && pesquisa.getDataFim() != null) {
            result.rejectValue("dataFim", "dataFim.invalida", "Pesquisa em andamento NÃO pode ter data de fim preenchida!");
        }

        if (result.hasErrors()) {
            model.addAttribute("hoje", java.time.LocalDate.now());
            return "pesquisas/cadastrar";
        }


        try {
            service.salvar(pesquisa);
            model.addAttribute("mensagem", "Pesquisa salva com sucesso!");
            return "redirect:/pesquisas/" + pesquisa.getStatus();
        } catch (IllegalArgumentException ex) {

            result.rejectValue("dataFim", "dataFim.invalida", ex.getMessage());
            model.addAttribute("hoje", java.time.LocalDate.now());
            return "pesquisas/cadastrar";
        }
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Pesquisa pesquisa = service.buscarPorId(id).orElse(null);
        if (pesquisa == null) {
            model.addAttribute("erro", "Pesquisa não encontrada");
            return "redirect:/pesquisas/EM_ANDAMENTO";
        }
        model.addAttribute("pesquisa", pesquisa);
        model.addAttribute("hoje", java.time.LocalDate.now());
        return "pesquisas/cadastrar";
    }


    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Integer id,
            @RequestParam Status status,
            Model model
    ) {
        try {
            service.deletar(id);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("erro", ex.getMessage());
        }
        return "redirect:/pesquisas/" + status;
    }
}