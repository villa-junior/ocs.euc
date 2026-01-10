package br.edu.ifba.ocs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/sobre")
public class SobreWebController {
    @GetMapping()
    public String sobre() {

        return "sobre";
    }
}
