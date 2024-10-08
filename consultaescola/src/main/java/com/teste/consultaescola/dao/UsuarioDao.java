package com.teste.consultaescola.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.consultaescola.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
}
