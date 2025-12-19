package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Obra;
import br.edu.ifba.ocs.model.Categoria;
import br.edu.ifba.ocs.service.ObraService;
import br.edu.ifba.ocs.service.CategoriaService;
import br.edu.ifba.ocs.service.AutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/obras")
public class ObraWebController {

    @Autowired
    private ObraService obraService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AutoraService autoraService;

    /* ================= LISTAGEM ================= */

    @GetMapping
    public String listarTodas(Model model) {
        model.addAttribute("obras",
                obraService.listarOrdenadoPorAnoDesc());
        model.addAttribute("categoria", null);
        return "obras/listar";
    }

    @GetMapping("/categoria/{id}")
    public String listarPorCategoria(@PathVariable Integer id, Model model) {

        return categoriaService.buscarPorId(id)
                .map(categoria -> {
                    model.addAttribute("obras",
                            obraService.listarPorCategoria(id));
                    model.addAttribute("categoria", categoria);
                    return "obras/listar";
                })
                .orElse("redirect:/obras");
    }

    /* ================= CADASTRAR ================= */

    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        model.addAttribute("obra", new Obra());
        model.addAttribute("categorias",
                categoriaService.listar());
        model.addAttribute("autoras",
                autoraService.listar());
        return "obras/cadastrar";
    }

    @PostMapping
    public String salvar(
            @ModelAttribute Obra obra,
            @RequestParam Integer categoriaId,
            @RequestParam(required = false) List<Integer> autorasIds
    ) {

        Categoria categoria = categoriaService
                .buscarPorId(categoriaId)
                .orElse(null);

        if (categoria == null) {
            return "redirect:/obras/cadastrar";
        }

        obra.setCategoria(categoria);

        Obra salva =
                obraService.salvarComAutoras(obra, autorasIds);

        return "redirect:/obras/categoria/" + categoria.getId();
    }

    /* ================= EDITAR ================= */

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        return obraService.buscarPorId(id)
                .map(obra -> {
                    model.addAttribute("obra", obra);
                    model.addAttribute("categorias",
                            categoriaService.listar());
                    model.addAttribute("autoras",
                            autoraService.listar());
                    return "obras/editar";
                })
                .orElse("redirect:/obras");
    }

    @PostMapping("/editar/{id}")
    public String atualizar(
            @PathVariable Integer id,
            @ModelAttribute Obra obra,
            @RequestParam Integer categoriaId,
            @RequestParam(required = false) List<Integer> autorasIds
    ) {

        Categoria categoria = categoriaService
                .buscarPorId(categoriaId)
                .orElse(null);

        if (categoria == null) {
            return "redirect:/obras";
        }

        obra.setId(id);
        obra.setCategoria(categoria);

        Obra atualizada =
                obraService.salvarComAutoras(obra, autorasIds);

        return "redirect:/obras/categoria/" + categoria.getId();
    }

    /* ================= EXCLUIR ================= */

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        obraService.deletar(id);
        return "redirect:/obras";
    }
}
