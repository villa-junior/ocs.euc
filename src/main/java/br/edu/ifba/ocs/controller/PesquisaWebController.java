package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import br.edu.ifba.ocs.security.ContaDetails;
import br.edu.ifba.ocs.service.PesquisaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/pesquisas")
public class PesquisaWebController {

    @Autowired
    private PesquisaService service;


    @GetMapping("/public/{status}")
    public String listarPublico(
            @PathVariable Status status,
            Model model,
            @AuthenticationPrincipal ContaDetails usuarioLogado
    ) {
        model.addAttribute("pesquisas", service.listarPorStatus(status));
        model.addAttribute("status", status);

        if (usuarioLogado != null) {
            model.addAttribute("idContaLogada", usuarioLogado.getConta().getId());
        }

        return "pesquisas/listar";
    }


    @GetMapping("/{status}")
    public String listarPrivado(
            @PathVariable Status status,
            Model model,
            @AuthenticationPrincipal ContaDetails usuarioLogado
    ) {
        model.addAttribute("pesquisas", service.listarPorStatus(status));
        model.addAttribute("status", status);

        if (usuarioLogado != null) {
            model.addAttribute("idContaLogada", usuarioLogado.getConta().getId());
        }

        return "pesquisas/listar";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        model.addAttribute("pesquisa", new Pesquisa());
        model.addAttribute("hoje", LocalDate.now());
        return "pesquisas/cadastrar";
    }

    @PostMapping
    public String salvar(
            @Valid @ModelAttribute("pesquisa") Pesquisa pesquisa,
            BindingResult result,
            Model model,
            @AuthenticationPrincipal ContaDetails usuarioLogado
    ) {

        if (pesquisa.getDataInicio() != null &&
                pesquisa.getDataFim() != null &&
                pesquisa.getDataFim().isBefore(pesquisa.getDataInicio())) {
            result.rejectValue(
                    "dataFim",
                    "dataFim.invalida",
                    "A data de fim não pode ser anterior à data de início."
            );
        }

        if (pesquisa.getStatus() == Status.EM_ANDAMENTO &&
                pesquisa.getDataFim() != null) {
            result.rejectValue(
                    "dataFim",
                    "dataFim.invalida",
                    "Pesquisa em andamento NÃO pode ter data de fim."
            );
        }

        if (result.hasErrors()) {
            model.addAttribute("hoje", LocalDate.now());
            return "pesquisas/cadastrar";
        }

        pesquisa.setConta(usuarioLogado.getConta());
        service.salvar(pesquisa);

        return "redirect:/pesquisas/" + pesquisa.getStatus();
    }


    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable UUID id,
            Model model,
            @AuthenticationPrincipal ContaDetails usuarioLogado
    ) {
        Pesquisa pesquisa = service.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Pesquisa não encontrada"));

        if (pesquisa.getConta() == null ||
                !pesquisa.getConta().getId().equals(usuarioLogado.getConta().getId())) {
            throw new IllegalArgumentException("Você não tem permissão para editar esta pesquisa.");
        }

        model.addAttribute("pesquisa", pesquisa);
        model.addAttribute("hoje", LocalDate.now());
        return "pesquisas/cadastrar";
    }

    @PostMapping("/editar/{id}")
    public String salvarEdicao(
            @PathVariable UUID id,
            @Valid @ModelAttribute("pesquisa") Pesquisa pesquisa,
            BindingResult result,
            Model model,
            @AuthenticationPrincipal ContaDetails usuarioLogado
    ) {
        if (result.hasErrors()) {
            model.addAttribute("hoje", LocalDate.now());
            return "pesquisas/cadastrar";
        }

        service.editar(id, pesquisa, usuarioLogado.getConta());
        return "redirect:/pesquisas/" + pesquisa.getStatus();
    }


    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable UUID id,
            @RequestParam Status status,
            @AuthenticationPrincipal ContaDetails usuarioLogado
    ) {
        Pesquisa pesquisa = service.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Pesquisa não encontrada"));

        if (pesquisa.getConta() == null ||
                !pesquisa.getConta().getId().equals(usuarioLogado.getConta().getId())) {
            throw new IllegalArgumentException("Você não tem permissão para excluir esta pesquisa.");
        }

        service.deletar(id, usuarioLogado.getConta());
        return "redirect:/pesquisas/" + status;
    }
}
