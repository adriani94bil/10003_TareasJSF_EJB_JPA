/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.servicios;

import com.tareas.entidades.*;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface TareasServiceLocal {
    public Collection<Tarea> getTareas(Integer idUsuario, String estado);
    
}
