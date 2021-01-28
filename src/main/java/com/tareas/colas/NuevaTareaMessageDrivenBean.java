/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.colas;

import com.tareas.entidades.Tarea;
import com.tareas.entidades.Usuario;
import com.tareas.servicios.TareasServiceLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author user
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/colaNuevasTareas"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NuevaTareaMessageDrivenBean implements MessageListener {
    
    @EJB
    private TareasServiceLocal tareaService;
    
    public NuevaTareaMessageDrivenBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg=(TextMessage) message;
            System.out.println("......recibo el mensjae"+msg.getText());
            
            Tarea t=new Tarea();
            t.setDescripcion(msg.getText());
            t.setEstado("TO DO");
            t.setActiva(true);
            Usuario u=new Usuario();
            u.setIdUsuario(3);
            t.setUsuario(u);
            tareaService.altaTarea(t);
        } catch (JMSException ex) {
            Logger.getLogger(NuevaTareaMessageDrivenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
