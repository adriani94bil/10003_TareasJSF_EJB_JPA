/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.web;

import com.tareas.entidades.Tarea;
import com.tareas.servicios.TareasServiceLocal;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author user
 */
@Named(value = "gestionTareasMB")
@RequestScoped
public class GestionTareasManagedBean {

    private Collection<Tarea> coleccionTareasTODO;
    
    @EJB
    private TareasServiceLocal tareasService;
    
    public GestionTareasManagedBean() {
        
    }
    @PostConstruct
    public  void iniciar(){
        this.coleccionTareasTODO=this.tareasService.getTareas(1, "TO DO");
    }

    public Collection<Tarea> getColeccionTareasTODO() {
        return coleccionTareasTODO;
    }
    
    
    
}
