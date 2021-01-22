/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.servicios;

import com.tareas.entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class UsuarioService implements UsuarioServiceLocal {

    @PersistenceContext(unitName="TareasPU")
    private EntityManager em;

    @Override
    public Usuario getUsuario(int id) {
        Usuario u=em.find(Usuario.class, id);
        return u;
    }

    @Override
    public void alta(Usuario usr) {
        em.persist(usr);
    }
    
    
}
