package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Obra;
import br.edu.ifba.ocs.service.AutoraService;
import br.edu.ifba.ocs.service.CategoriaService;
import br.edu.ifba.ocs.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/obras")
public class ObraWebController {

    @Autowired
    private ObraService obraService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AutoraService autoraService;



    private void prepararModelPaginado(Model model, Page<Obra> page) {
        model.addAttribute("obras", page.getContent());
        model.addAttribute("paginaAtual", page.getNumber());
        model.addAttribute("totalPaginas", page.getTotalPages());

        Map<UUID, List<String>> autorasPorObra = new HashMap<>();
        for (Obra o : page.getContent()) {
            autorasPorObra.put(o.getId(), obraService.buscarNomesAutoras(o.getId()));
        }
        model.addAttribute("autorasPorObra", autorasPorObra);
    }


    @GetMapping("/categoria/{id}")
    public String listarPorCategoria(@PathVariable UUID id, @RequestParam(defaultValue = "0") int page, Model model) {
        return categoriaService.buscarPorId(id).map(cat -> {
            Page<Obra> obrasPage = obraService.listarPorCategoriaPaginado(
                    id, PageRequest.of(page, 9, Sort.by("anoPublicacao").descending())
            );
            prepararModelPaginado(model, obrasPage);
            model.addAttribute("categoria", cat);
            model.addAttribute("urlBase", "/obras/categoria/" + id);
            return "obras/listar";
        }).orElse("redirect:/obras");
    }

    @GetMapping
    public String listarTodas(Model model) {

        List<Obra> obras = obraService.listarOrdenadoPorAnoDesc();
        model.addAttribute("obras", obras);
        model.addAttribute("categoria", null);

        Map<UUID, List<String>> autorasPorObra = new HashMap<>();
        for (Obra o : obras) {
            autorasPorObra.put(
                    o.getId(),
                    obraService.buscarNomesAutoras(o.getId())
            );
        }

        model.addAttribute("autorasPorObra", autorasPorObra);
        return "obras/listar";
    }




    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {

        model.addAttribute("obra", new Obra());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("autoras", autoraService.listar());

        return "obras/cadastrar";
    }

    @PostMapping
    public String salvar(
            @ModelAttribute Obra obra,
            @RequestParam(required = false) List<UUID> autorasIds
    ) {

        if (obra.getCategoria() == null || obra.getCategoria().getId() == null) {
            return "redirect:/obras/cadastrar";
        }

        obraService.salvarComAutoras(
                obra,
                obra.getCategoria().getId(),
                autorasIds
        );

        return "redirect:/obras/categoria/" + obra.getCategoria().getId();
    }



    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model) {

        return obraService.buscarPorId(id)
                .map(obra -> {

                    model.addAttribute("obra", obra);
                    model.addAttribute("categorias", categoriaService.listar());
                    model.addAttribute("autoras", autoraService.listar());

                    model.addAttribute(
                            "autorasSelecionadas",
                            obraService.buscarIdsAutoras(id)
                    );

                    return "obras/cadastrar";
                })
                .orElse("redirect:/obras");
    }

    @PostMapping("/editar/{id}")
    public String atualizar(
            @PathVariable UUID id,
            @ModelAttribute Obra obra,
            @RequestParam(required = false) List<UUID> autorasIds
    ) {

        if (obra.getCategoria() == null || obra.getCategoria().getId() == null) {
            return "redirect:/obras";
        }

        obra.setId(id);

        obraService.salvarComAutoras(
                obra,
                obra.getCategoria().getId(),
                autorasIds
        );

        return "redirect:/obras/categoria/" + obra.getCategoria().getId();
    }



    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id) {

        obraService.deletar(id);
        return "redirect:/obras";
    }
}