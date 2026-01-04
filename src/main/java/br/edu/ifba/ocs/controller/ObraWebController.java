package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Obra;
import br.edu.ifba.ocs.service.AutoraService;
import br.edu.ifba.ocs.service.CategoriaService;
import br.edu.ifba.ocs.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/categoria/{id}")
    public String listarPorCategoria(@PathVariable UUID id, Model model) {

        return categoriaService.buscarPorId(id)
                .map(categoria -> {

                    List<Obra> obras = obraService.listarPorCategoria(id);

                    model.addAttribute("obras", obras);
                    model.addAttribute("categoria", categoria);

                    Map<UUID, List<String>> autorasPorObra = new HashMap<>();
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

                    return "obras/cadastrar"; // reutiliza o mesmo formulário
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
