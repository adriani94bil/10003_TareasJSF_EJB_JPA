/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.web;

import com.tareas.entidades.Tarea;
import com.tareas.entidades.Usuario;
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
import javax.inject.Inject;

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
    private Tarea nuevaTarea;
    
    @Inject
    private LoginManagedBean loginMB;
    
    private Usuario usuarioLog;
    
    @EJB
    private TareasServiceLocal tareasService;
    
    public GestionTareasManagedBean() {
        
    }
    @PostConstruct
    public void iniciar(){
        this.usuarioLog=loginMB.getUsuarioLog();
        this.coleccionTareasTODO=this.tareasService.getTareas(usuarioLog.getIdUsuario(), "TO DO");
        this.coleccionTareasINPROGRESS=this.tareasService.getTareas(usuarioLog.getIdUsuario(), "IN PROGRESS");
        this.coleccionTareasDONE=this.tareasService.getTareas(usuarioLog.getIdUsuario(), "DONE");
        this.tareaSelec=new Tarea();
        this.nuevaTarea=new Tarea();
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

    public Tarea getNuevaTarea() {
        return nuevaTarea;
    }

    public void setNuevaTarea(Tarea nuevaTarea) {
        this.nuevaTarea = nuevaTarea;
    }
    

    public Usuario getUsuarioLog() {
        return usuarioLog;
    }

    public void setUsuarioLog(Usuario usuarioLog) {
        this.usuarioLog = usuarioLog;
    }
    
    public String altaTarea(){
        this.nuevaTarea.setEstado("TO DO");
        this.nuevaTarea.setUsuario(usuarioLog);
        this.nuevaTarea.setActiva(true);
        tareasService.altaTarea(nuevaTarea);
        return "lista-tareas?faces-redirect=true";
    }
    
    public  String updateInProgress(Tarea t){
        try {
            this.tareasService.modificarEstado(t,"IN PROGRESS" );
            //Recargo los listados
            this.iniciar();
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
            this.iniciar();
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
            this.iniciar();
        } catch (TareaNotFoundException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        } catch (TareaUpdateException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo modificar"+ex.getMessage()) );
        }
        return "lista-tareas";
    }
    public String borrarTarea(int id){
        try {
            this.tareasService.borrarTarea(id);
            this.iniciar();
        } catch (TareaNotFoundException ex) {
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo borrar"+ex.getMessage()) );
        }
        return "lista-tareas";
    }
    
    
    
    
}
