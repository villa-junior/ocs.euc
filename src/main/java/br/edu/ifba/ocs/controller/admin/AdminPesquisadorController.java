package br.edu.ifba.ocs.controller.admin;

import br.edu.ifba.ocs.service.ContaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin/pesquisadores")
public class AdminPesquisadorController {

    private final ContaService contaService;

    public AdminPesquisadorController(ContaService contaService) {
        this.contaService = contaService;
    }


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pesquisadores",
                contaService.listarPesquisadoresPendentes());
        return "admin/pesquisadores";
    }



    @PostMapping("/{id}/aprovar")
    public String aprovar(@PathVariable UUID id) {
        contaService.aprovarPesquisador(id);
        return "redirect:/admin/pesquisadores";
    }


    @PostMapping("/{id}/rejeitar")
    public String rejeitar(@PathVariable UUID id) {
        contaService.rejeitarPesquisador(id);
        return "redirect:/admin/pesquisadores";
    }
}
