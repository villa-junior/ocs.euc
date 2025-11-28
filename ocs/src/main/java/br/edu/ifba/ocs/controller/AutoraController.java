package br.edu.ifba.ocs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifba.ocs.model.Autora;
import br.edu.ifba.ocs.repository.AutoraRepository;

@Controller	
@RequestMapping("/autoras")
public class AutoraController {
	@Autowired
    private AutoraRepository autoraRepository;

	
	@GetMapping("/todos")
    public List<Autora> listarTodos(@RequestParam(required = false) String nome, Model model) {

        return autoraRepository.findAll();

    }


	
}
