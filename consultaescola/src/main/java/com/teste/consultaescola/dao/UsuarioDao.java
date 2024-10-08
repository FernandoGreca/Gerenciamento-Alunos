package com.teste.consultaescola.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teste.consultaescola.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    @Query("select a from Usuario a where a.email= :email")
    public Usuario findByEmail(String email);
}
