/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.servicios;

import com.tareas.entidades.Tarea;
import com.tareas.excepciones.TareaNotFoundException;
import com.tareas.excepciones.TareaUpdateException;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class TareasService implements TareasServiceLocal {

    @PersistenceContext(unitName="TareasPU")
    private EntityManager em;
    
    @Override
    public Tarea getTarea(Integer id) throws TareaNotFoundException {
        Tarea t=em.find(Tarea.class, id);
        if (t==null) {
            throw new TareaNotFoundException("No existe la tarea solicitada");
        }
        return t;
    }
    
    @Override
    public Collection<Tarea> getTareas(Integer idUsuario, String estado) {
        
        Query query= em.createNamedQuery("Tarea.findByEstadoAndUserId");
        query.setParameter("estado", estado);
        query.setParameter("idUsuario",idUsuario);
        return query.getResultList();
    }

    @Override
    public void modificarEstado(Tarea tarea, String nuevoEstado) throws TareaNotFoundException, TareaUpdateException {
        // Traigo la tarea de la DB
        Tarea tareaDB=this.getTarea(tarea.getIdTarea());
        tareaDB.setEstado(nuevoEstado);
        //Al finalizar el método realiza el commit
        
    }


}
