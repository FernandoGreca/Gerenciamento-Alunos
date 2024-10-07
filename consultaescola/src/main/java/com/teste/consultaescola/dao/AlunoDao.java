package com.teste.consultaescola.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.teste.consultaescola.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer>{
    
    @Query("select a from Aluno a where a.status = 'ATIVO'")
    public List<Aluno> findByStatusAtivo();

    @Query("select a from Aluno a where a.status = 'INATIVO'")
    public List<Aluno> findByStatusInativo();

    @Query("select a from Aluno a where a.status = 'TRANCADO'")
    public List<Aluno> findByStatusTrancado();

    @Query("select a from Aluno a where a.status = 'CANCELADO'")
    public List<Aluno> findByStatusCancelado();
}
