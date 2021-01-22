/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.servicios;

import com.tareas.entidades.Usuario;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface UsuarioServiceLocal {
    public Usuario getUsuario(int id);
    public void alta(Usuario usrNuevo);
    
    
}


