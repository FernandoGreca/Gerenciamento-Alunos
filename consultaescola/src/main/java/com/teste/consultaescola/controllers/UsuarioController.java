package com.teste.consultaescola.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UsuarioController {
    
    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }
}
