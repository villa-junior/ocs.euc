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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/obras")
public class ObraWebController {

    @Autowired
    private ObraService obraService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AutoraService autoraService;



    @GetMapping
    public String listarTodas(Model model) {

        List<Obra> obras = obraService.listarOrdenadoPorAnoDesc();

        model.addAttribute("obras", obras);
        model.addAttribute("categoria", null);


        Map<Integer, List<String>> autorasPorObra = new HashMap<>();

        for (Obra o : obras) {
            autorasPorObra.put(
                    o.getId(),
                    obraService.buscarNomesAutoras(o.getId())
            );
        }

        model.addAttribute("autorasPorObra", autorasPorObra);

        return "obras/listar";
    }

    @GetMapping("/categoria/{id}")
    public String listarPorCategoria(@PathVariable Integer id, Model model) {

        return categoriaService.buscarPorId(id)
                .map(categoria -> {

                    List<Obra> obras =
                            obraService.listarPorCategoria(id);

                    model.addAttribute("obras", obras);
                    model.addAttribute("categoria", categoria);

                    Map<Integer, List<String>> autorasPorObra = new HashMap<>();

                    for (Obra o : obras) {
                        autorasPorObra.put(
                                o.getId(),
                                obraService.buscarNomesAutoras(o.getId())
                        );
                    }

                    model.addAttribute("autorasPorObra", autorasPorObra);

                    return "obras/listar";
                })
                .orElse("redirect:/obras");
    }



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

        obraService.salvarComAutoras(obra, autorasIds);

        return "redirect:/obras/categoria/" + categoria.getId();
    }



    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        return obraService.buscarPorId(id)
                .map(obra -> {

                    model.addAttribute("obra", obra);
                    model.addAttribute("categorias",
                            categoriaService.listar());
                    model.addAttribute("autoras",
                            autoraService.listar());

                    // 🔹 IDs das autoras para marcar checkboxes
                    model.addAttribute(
                            "autorasSelecionadas",
                            obraService.buscarIdsAutoras(id)
                    );

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

        obraService.salvarComAutoras(obra, autorasIds);

        return "redirect:/obras/categoria/" + categoria.getId();
    }



    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        obraService.deletar(id);
        return "redirect:/obras";
    }
}
