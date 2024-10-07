package com.teste.consultaescola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teste.consultaescola.dao.AlunoDao;
import com.teste.consultaescola.model.Aluno;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;




@Controller
public class AlunoController {

    @Autowired
    private AlunoDao alunoRepositorio;

    @GetMapping("/form-aluno")
    public ModelAndView aluno(Aluno aluno) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/form-aluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }
    
    @PostMapping("InserirAlunos")
    public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        
        if (br.hasErrors()) {
            mv.setViewName("aluno/form-aluno");
            mv.addObject("aluno");
        } else {
            mv.setViewName("redirect:/alunos-cadastrados");
            alunoRepositorio.save(aluno);
            return mv;
        }
    
        return mv;
    }


    @GetMapping("/alunos-cadastrados")
    public ModelAndView alunosCadastrados() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/listAlunos");
        mv.addObject("alunosList", alunoRepositorio.findAll());
        return mv;
    }
    

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alterar");
        Aluno aluno = alunoRepositorio.getReferenceById(id);
        mv.addObject("aluno", aluno);
        return mv;
    }
    
    @PostMapping("alterar")
    public ModelAndView alterar(Aluno aluno, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        String msgPadrao = "Ao começar a editar um aluno você não pode deixar o campo ";

        if (aluno.getNome().isEmpty()) {
            redirectAttributes.addFlashAttribute("alterarMsg", msgPadrao + "NOME vazio!!");
        } else if (aluno.getCurso() == null) {
            redirectAttributes.addFlashAttribute("alterarMsg", msgPadrao + "CURSO vazio!!");
        } else if (aluno.getStatus() == null) {
            redirectAttributes.addFlashAttribute("alterarMsg", msgPadrao + "STATUS vazio!!");
        } else if (aluno.getTurno().isEmpty()) {
            redirectAttributes.addFlashAttribute("alterarMsg", msgPadrao + "TURNO vazio!!");
        } else {
            alunoRepositorio.save(aluno);
            mv.setViewName("redirect:/alunos-cadastrados");
            return mv;
        }

        mv.setViewName("redirect:/alterar/" + aluno.getId());
        return mv;
    }
    

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Integer id) {
        alunoRepositorio.deleteById(id);
        return "redirect:/alunos-cadastrados";
    }

    @GetMapping("/filtro-alunos")
    public ModelAndView filtroAlunos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/filtro-alunos");
        return mv;
    }

    @GetMapping("/alunos-ativos")
    public ModelAndView listaAlunosAtivos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alunos-ativos");
        mv.addObject("alunosAtivos", alunoRepositorio.findByStatusAtivo());
        return mv;
    }
    
    @GetMapping("/alunos-inativos")
    public ModelAndView listaAlunosInativos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alunos-inativos");
        mv.addObject("alunosInativos", alunoRepositorio.findByStatusInativo());
        return mv;
    }

    @GetMapping("/alunos-trancados")
    public ModelAndView listaAlunosTrancados() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alunos-trancados");
        mv.addObject("alunosTrancados", alunoRepositorio.findByStatusTrancado());
        return mv;
    }

    @GetMapping("/alunos-cancelados")
    public ModelAndView listaAlunosCancelados() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alunos-cancelados");
        mv.addObject("alunosCancelados", alunoRepositorio.findByStatusCancelado());
        return mv;
    }

    @PostMapping("pesquisar-aluno")
    public ModelAndView pesquisasrAluno(@RequestParam(required = false) String nome) {
        ModelAndView mv = new ModelAndView();
        List<Aluno> listaAlunos;
        if (nome == null || nome.trim().isEmpty()) {
            listaAlunos = alunoRepositorio.findAll();
        } else {
            listaAlunos = alunoRepositorio.findByNomeContainingIgnoreCase(nome);
        }
        mv.addObject("listaDeAlunos", listaAlunos);
        mv.setViewName("aluno/pesquisa-resultado");
        return mv;
    }
    
}
