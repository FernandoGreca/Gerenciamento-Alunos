package com.teste.consultaescola.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teste.consultaescola.exceptions.ServiceExc;
import com.teste.consultaescola.model.Aluno;
import com.teste.consultaescola.model.Usuario;
import com.teste.consultaescola.service.ServiceUsuario;
import com.teste.consultaescola.util.Util;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsuarioController {

    @Autowired
    private ServiceUsuario serviceUsuario;
    
    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index");
        mv.addObject("aluno", new Aluno());
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
    public ModelAndView salavarUsuario(Usuario usuario) throws Exception {
        ModelAndView mv = new ModelAndView();
        serviceUsuario.salvarUsuario(usuario);
        mv.setViewName("redirect:/");
        return mv;
    }
    
    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        if (br.hasErrors()) {
            mv.setViewName("login/login");
        }

        Usuario userLogin = serviceUsuario.loginUser(usuario.getUser(), Util.md5(usuario.getSenha()));
        if (userLogin == null) {
            mv.addObject("msg", "Usuário não encontrado. Tente novamente");
        } else {
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }
        
        return mv;
    }
    
}