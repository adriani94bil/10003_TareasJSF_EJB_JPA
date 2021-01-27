/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.web;

import com.tareas.entidades.Usuario;
import com.tareas.excepciones.UsuarioNotFoundException;
import com.tareas.servicios.UsuarioServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author user
 */
@Named(value = "pruebasMB")
@RequestScoped
public class PruebasManagedBean {

    
    @EJB
    private UsuarioServiceLocal usuarioService;
    
    private Usuario usuarioEncontrado;
    private Usuario usuarioRegister ;
    //ctr
    public PruebasManagedBean() {
        
    }
    @PostConstruct
    public void iniciar(){
        this.usuarioRegister=new Usuario();
    }
    public Usuario getUsuarioEncontrado() {
        return usuarioEncontrado;
    }

    public void setUsuarioEncontrado(Usuario usuarioEncontrado) {
        this.usuarioEncontrado = usuarioEncontrado;
    }

    public Usuario getUsuarioRegister() {
        return usuarioRegister;
    }

    public void setUsuarioRegister(Usuario usuarioRegister) {
        this.usuarioRegister = usuarioRegister;
    }
    
    
    // action
    public void mostrarUsuario(int i){
        try {
            this.usuarioEncontrado=usuarioService.getUsuario(i);
        } catch (UsuarioNotFoundException ex) {
            Logger.getLogger(PruebasManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String altaUsuario(){
        usuarioService.alta(usuarioRegister);
        return "login?faces-redirect=true";
    }
}
