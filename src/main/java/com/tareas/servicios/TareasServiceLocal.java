/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.servicios;

import com.tareas.entidades.*;
import com.tareas.excepciones.TareaNotFoundException;
import com.tareas.excepciones.TareaUpdateException;
import java.util.Collection;
import javax.ejb.Local;
import org.eclipse.persistence.exceptions.DatabaseException;

@Local
public interface TareasServiceLocal {
    public Tarea getTarea(Integer idTarea) throws TareaNotFoundException;
    public Collection<Tarea> getTareas(Integer idUsuario, String estado);
    public void modificarEstado(Tarea tarea,String estado) throws TareaNotFoundException,TareaUpdateException;
    public void altaTarea(Tarea t) throws DatabaseException;
    public void borrarTarea(int id) throws TareaNotFoundException;
}
