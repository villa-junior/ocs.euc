package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.dto.CadastroContaDTO;
import br.edu.ifba.ocs.model.Perfil;
import br.edu.ifba.ocs.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/contas")
public class CadastroContaController {

    private final ContaService contaService;

    public CadastroContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/nova")
    public String formulario(Model model) {

        model.addAttribute("cadastroDto", new CadastroContaDTO());
        model.addAttribute("perfis", perfisPermitidos());
        return "contas/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("cadastroDto") CadastroContaDTO dto,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            model.addAttribute("perfis", perfisPermitidos());
            return "contas/cadastro";
        }

        try {

            contaService.cadastrar(dto);
        } catch (RuntimeException e) {

            model.addAttribute("erro", e.getMessage());
            model.addAttribute("perfis", perfisPermitidos());
            return "contas/cadastro";
        }


        if (dto.getPerfilDesejado() == Perfil.pesquisador) {
            return "redirect:/login?pedidoEmAnalise";
        }

        return "redirect:/login?cadastroSucesso";
    }


    private Perfil[] perfisPermitidos() {
        return Arrays.stream(Perfil.values())
                .filter(p -> p != Perfil.admin)
                .toArray(Perfil[]::new);
    }
}