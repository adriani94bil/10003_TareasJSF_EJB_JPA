/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.web;

import com.tareas.entidades.Tarea;
import com.tareas.excepciones.TareaNotFoundException;
import com.tareas.excepciones.TareaUpdateException;
import com.tareas.servicios.TareasServiceLocal;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
@Named(value = "gestionTareasMB")
@RequestScoped
public class GestionTareasManagedBean {

    private Collection<Tarea> coleccionTareasTODO;
    private Collection<Tarea> coleccionTareasINPROGRESS;
    private Collection<Tarea> coleccionTareasDONE;
    private Tarea tareaSelec;
    
    
    @EJB
    private TareasServiceLocal tareasService;
    
    public GestionTareasManagedBean() {
        
    }
    @PostConstruct
    public void iniciar(){
        this.coleccionTareasTODO=this.tareasService.getTareas(1, "TO DO");
        this.coleccionTareasINPROGRESS=this.tareasService.getTareas(1, "IN PROGRESS");
        this.coleccionTareasDONE=this.tareasService.getTareas(1, "DONE");
        this.tareaSelec=new Tarea();
    }

    public Collection<Tarea> getColeccionTareasTODO() {
        return coleccionTareasTODO;
    }

    public Collection<Tarea> getColeccionTareasINPROGRESS() {
        return coleccionTareasINPROGRESS;
    }

    public Collection<Tarea> getColeccionTareasDONE() {
        return coleccionTareasDONE;
    }

    public Tarea getTareaSelec() {
        return tareaSelec;
    }

    public void setTareaSelec(Tarea tareaSelec) {
        this.tareaSelec = tareaSelec;
    }
    
    
    public  String updateInProgress(Tarea t){
        try {
            this.tareasService.modificarEstado(t,"IN PROGRESS" );
            //Recargo los listados
            this.coleccionTareasTODO=tareasService.getTareas(1, "TO DO");
            this.coleccionTareasINPROGRESS=tareasService.getTareas(1, "IN PROGRESS");
            this.coleccionTareasDONE=tareasService.getTareas(1, "DONE");
        } catch (TareaNotFoundException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        } catch (TareaUpdateException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        }
        return "lista-tareas";
    }
    public  String updateToDo(Tarea t){
        try {
            this.tareasService.modificarEstado(t,"TO DO" );
            this.coleccionTareasTODO=tareasService.getTareas(1, "TO DO");
            this.coleccionTareasINPROGRESS=tareasService.getTareas(1, "IN PROGRESS");
            this.coleccionTareasDONE=tareasService.getTareas(1, "DONE");
        } catch (TareaNotFoundException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        } catch (TareaUpdateException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        }
        return "lista-tareas";
    }
    public  String updateDONE(Tarea t){
        try {
            this.tareasService.modificarEstado(t,"DONE" );
            this.coleccionTareasTODO=tareasService.getTareas(1, "TO DO");
            this.coleccionTareasINPROGRESS=tareasService.getTareas(1, "IN PROGRESS");
            this.coleccionTareasDONE=tareasService.getTareas(1, "DONE");
        } catch (TareaNotFoundException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        } catch (TareaUpdateException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        }
        return "lista-tareas";
    }
    
    
    
    
}
