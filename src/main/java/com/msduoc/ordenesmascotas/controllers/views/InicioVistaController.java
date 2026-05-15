package com.msduoc.ordenesmascotas.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioVistaController {

    @GetMapping("/app")
    public String inicio(Model model) {
        model.addAttribute("titulo", "Bienvenido a la veterinaria");
        return "index";
    }
    
}
