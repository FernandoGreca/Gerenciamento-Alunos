package com.teste.consultaescola.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import com.teste.consultaescola.model.Usuario;

@Controller
public class UsuarioController {
    
    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/cadastro");
        mv.addObject("aluno", new Usuario());
        return mv;
    }
    
}
