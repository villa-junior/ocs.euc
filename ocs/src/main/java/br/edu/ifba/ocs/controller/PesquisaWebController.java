package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Pesquisa;
import br.edu.ifba.ocs.model.Pesquisa.Status;
import br.edu.ifba.ocs.security.ContaDetails;
import br.edu.ifba.ocs.service.PesquisaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/pesquisas")
public class PesquisaWebController {

    private final PesquisaService service;

    public PesquisaWebController(PesquisaService service) {
        this.service = service;
    }

    @GetMapping({ "/{status}", "/public/{status}" })
    public String listar(
            @PathVariable Status status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request,
            Model model,
            @AuthenticationPrincipal ContaDetails usuarioLogado
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataInicio").descending());
        Page<Pesquisa> paginaDePesquisas = service.listarPorStatus(status, pageable);

        model.addAttribute("pesquisas", paginaDePesquisas.getContent());
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", paginaDePesquisas.getTotalPages());
        model.addAttribute("status", status);


        model.addAttribute("urlBase", "/pesquisas/" + status);

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
        validarDatas(pesquisa, result);

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
        Pesquisa pesquisa = buscarPesquisaOuFalhar(id);
        validarPermissao(pesquisa, usuarioLogado);

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
        validarDatas(pesquisa, result);

        if (result.hasErrors()) {
            model.addAttribute("hoje", LocalDate.now());
            return "pesquisas/cadastrar";
        }

        service.editar(id, pesquisa, usuarioLogado.getConta());

        return "redirect:/pesquisas/" + pesquisa.getStatus();
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id,
                          @RequestParam(required = false, defaultValue = "EM_ANDAMENTO") Status status,
                          @AuthenticationPrincipal ContaDetails usuarioLogado) {

        service.deletar(id, usuarioLogado.getConta());
        return "redirect:/pesquisas/" + status;
    }



    private Pesquisa buscarPesquisaOuFalhar(UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Pesquisa não encontrada"));
    }

    private void validarPermissao(Pesquisa pesquisa, ContaDetails usuarioLogado) {
        boolean ehDono = pesquisa.getConta() != null &&
                pesquisa.getConta().getId().equals(usuarioLogado.getConta().getId());

        boolean ehAdmin = usuarioLogado.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!ehDono && !ehAdmin) {
            throw new AccessDeniedException("Você não tem permissão para esta ação.");
        }
    }

    private void validarDatas(Pesquisa pesquisa, BindingResult result) {

        if (pesquisa.getDataInicio() != null &&
                pesquisa.getDataFim() != null &&
                pesquisa.getDataFim().isBefore(pesquisa.getDataInicio())) {
            result.rejectValue("dataFim", "dataFim.invalida",
                    "A data de fim não pode ser anterior à data de início.");
        }


        if (pesquisa.getStatus() == Status.EM_ANDAMENTO && pesquisa.getDataFim() != null) {
            result.rejectValue("dataFim", "dataFim.invalida",
                    "Pesquisa em andamento NÃO pode ter data de fim.");
        }


        if (pesquisa.getStatus() == Status.CONCLUIDA && pesquisa.getDataFim() == null) {
            result.rejectValue("dataFim", "dataFim.obrigatoria",
                    "Uma pesquisa concluída deve obrigatoriamente ter uma data de fim.");
        }
    }
}