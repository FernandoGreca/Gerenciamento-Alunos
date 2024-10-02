package com.teste.consultaescola.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.consultaescola.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer>{
    
}
