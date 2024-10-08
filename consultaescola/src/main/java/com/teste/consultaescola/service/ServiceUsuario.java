package com.teste.consultaescola.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.consultaescola.dao.UsuarioDao;
import com.teste.consultaescola.exceptions.CriptoExistException;
import com.teste.consultaescola.exceptions.EmailExistException;
import com.teste.consultaescola.model.Usuario;
import com.teste.consultaescola.util.Util;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioDao usuarioRepositorio;
    
    public void salvarUsuario(Usuario usuario) throws Exception{
        
        try {
            
            if (usuarioRepositorio.findByEmail(usuario.getEmail()) != null) {
                throw new EmailExistException("Este email ja está sendo usado: " + usuario.getEmail());
            }

            usuario.setSenha(Util.md5(usuario.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistException("Erro na criptografia da senha");
        }

        usuarioRepositorio.save(usuario);
    }

}
