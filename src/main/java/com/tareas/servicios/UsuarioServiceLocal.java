/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.servicios;

import com.tareas.entidades.Usuario;
import com.tareas.excepciones.UsuarioNotFoundException;
import com.tareas.excepciones.UsuarioUpdateException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface UsuarioServiceLocal {
    public Usuario getUsuario(int id) throws UsuarioNotFoundException;
    public void alta(Usuario usrNuevo);
    public Collection<Usuario> getAllUsuarios();
    public Usuario getUsuarioByEmail(String email) throws UsuarioNotFoundException;
    public void borrar(int id) throws UsuarioNotFoundException;
    public void modificar(Usuario user) throws UsuarioNotFoundException, UsuarioUpdateException;
    
    
}


