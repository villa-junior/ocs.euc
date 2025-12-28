package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Pesquisa;
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
    public String listarPorStatus(@PathVariable String status, Model model) {
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
            BindingResult result
    ) {
        if (pesquisa.getDataInicio() != null && pesquisa.getDataFim() != null &&
                pesquisa.getDataFim().isBefore(pesquisa.getDataInicio())) {

            result.rejectValue(
                    "dataFim",
                    "dataFim.invalida",
                    "A data de fim não pode ser anterior à data de início"
            );
        }

        if (result.hasErrors()) {
            return "pesquisas/cadastrar";
        }

        service.salvar(pesquisa);
        return "redirect:/pesquisas/" + pesquisa.getStatus();
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("pesquisa", service.buscarPorId(id));
        return "pesquisas/cadastrar";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Integer id,
            @RequestParam String status
    ) {
        service.deletar(id);
        return "redirect:/pesquisas/" + status;
    }
}
