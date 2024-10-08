package com.teste.consultaescola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teste.consultaescola.dao.UsuarioDao;
import com.teste.consultaescola.model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioRepositorio;
    
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
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @PostMapping("salavarUsuario")
    public ModelAndView salavarUsuario(Usuario usuario) {
        ModelAndView mv = new ModelAndView();
        usuarioRepositorio.save(usuario);
        mv.setViewName("redirect:/");
        return mv;
    }
    
}