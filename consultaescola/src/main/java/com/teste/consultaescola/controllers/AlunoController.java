package com.teste.consultaescola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teste.consultaescola.dao.AlunoDao;
import com.teste.consultaescola.model.Aluno;

import org.springframework.web.bind.annotation.PostMapping;



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
    public ModelAndView inserirAluno(Aluno aluno, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        
        if (aluno.getNome().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Você deve preencher o campo NOME!!");
        } else if (aluno.getCurso() == null) {
            redirectAttributes.addFlashAttribute("msg", "Você deve preencher escolher um CURSO!!");
            
        } else if (aluno.getMatricula().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Você deve gerar uma MATRÍCULA!!");
        } else if (aluno.getStatus() == null) {
            redirectAttributes.addFlashAttribute("msg", "Você deve escolher um STATUS!!");
        } else if (aluno.getTurno().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Você deve preencher preencher o campo TURNO!!");
        } else {
            alunoRepositorio.save(aluno);
            mv.setViewName("redirect:/alunos-cadastrados");
            return mv;
        }
        mv.setViewName("redirect:/form-aluno");
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
        String padrao = "Ao começar a editar um aluno você não pode deixar o campo ";

        if (aluno.getNome().isEmpty()) {
            redirectAttributes.addFlashAttribute("alterarMsg", padrao + "NOME vazio!!");
        } else if (aluno.getCurso() == null) {
            redirectAttributes.addFlashAttribute("alterarMsg", padrao + "CURSO vazio!!");
            
        } else if (aluno.getStatus() == null) {
            redirectAttributes.addFlashAttribute("alterarMsg", padrao + "STATUS vazio!!");
        } else if (aluno.getTurno().isEmpty()) {
            redirectAttributes.addFlashAttribute("alterarMsg", padrao + "TURNO vazio!!");
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
    
}
