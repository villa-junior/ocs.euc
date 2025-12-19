package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Obra;
import br.edu.ifba.ocs.service.ObraService;
import br.edu.ifba.ocs.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/obras")
public class ObraWebController {

    @Autowired
    private ObraService obraService;

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public String listarTodas(Model model) {
        model.addAttribute("obras",
                obraService.listarOrdenadoPorAnoDesc());
        model.addAttribute("categoria", null);
        return "obras/listar";
    }


    @GetMapping("/categoria/{id}")
    public String listarPorCategoria(@PathVariable Integer id, Model model) {
        model.addAttribute("obras",
                obraService.listarPorCategoria(id));
        model.addAttribute("categoria",
                categoriaService.buscarPorId(id).orElseThrow());
        return "obras/listar";
    }


    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        model.addAttribute("obra", new Obra());
        model.addAttribute("categorias",
                categoriaService.listar());
        return "obras/cadastrar";
    }


    @PostMapping
    public String salvar(@ModelAttribute Obra obra) {
        Obra salva = obraService.salvar(obra);
        return "redirect:/obras/categoria/" + salva.getCategoria().getId();
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("obra",
                obraService.buscarPorId(id).orElseThrow());
        model.addAttribute("categorias",
                categoriaService.listar());
        return "obras/editar";
    }


    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Integer id,
                            @ModelAttribute Obra obra) {
        obra.setId(id);
        Obra atualizada = obraService.salvar(obra);
        return "redirect:/obras/categoria/" + atualizada.getCategoria().getId();
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        obraService.deletar(id);
        return "redirect:/obras";
    }
}
