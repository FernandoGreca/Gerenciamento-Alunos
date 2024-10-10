package com.teste.consultaescola.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teste.consultaescola.exceptions.EmailExistException;
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
    public ModelAndView salavarUsuario(@Valid Usuario usuario, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.addObject("msgErroCadastro", "Preencha todos os campos corretamente para efetuar seu cadastro");
            mv.setViewName("login/cadastro");
        } else {
            try {
                serviceUsuario.salvarUsuario(usuario);
                mv.setViewName("redirect:/");
            } catch (EmailExistException e) {
                mv.addObject("msgErroCadastro", e.getMessage());
                mv.setViewName("login/cadastro");
            } catch (Exception e) {
                mv.addObject("msgErroCadastro", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
                mv.setViewName("login/cadastro");
            }
        }
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        try {
            mv.addObject("usuario", new Usuario());
            if (br.hasErrors()) {
                mv.setViewName("login/login");
            }

            Usuario userLogin = serviceUsuario.loginUser(usuario.getUser(), Util.md5(usuario.getSenha()));

            if (userLogin == null) {
                mv.addObject("msg", "Usuário não encontrado. Tente novamente");
                mv.setViewName("login/login");
            } else {
                session.setAttribute("usuarioLogado", userLogin);
                return index();
            }

            return mv;

        } catch (NoSuchAlgorithmException | ServiceExc e) {
            mv.addObject("msg", "Usuário não encontrado. Tente novamente");
            mv.setViewName("login/login");
        } 

        return mv;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }

}