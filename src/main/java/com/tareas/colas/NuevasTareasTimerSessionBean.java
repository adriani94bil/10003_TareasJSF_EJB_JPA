/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.colas;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author user
 */
@Stateless
public class NuevasTareasTimerSessionBean {
    @Resource(mappedName="java:app/jms/TareasConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName="java:app/jms/colaNuevasTareas")
    private Queue cola;
    
    
    @Schedule(hour = "9/13", minute = "*/10",  persistent = false)
    
    public void myTimer() {
        String descripcion="Tarea "+(int)(Math.random()*100);
        System.out.println("envio el mensaje "+descripcion);
        try {
            
            Connection con=connectionFactory.createConnection();
            Session sesion=con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            TextMessage msg=sesion.createTextMessage(descripcion);
            MessageProducer productor= sesion.createProducer(cola);
            
            productor.send(msg);
            
            productor.close();
            sesion.close();
            con.close();
            
        } catch (JMSException ex) {
            Logger.getLogger(NuevasTareasTimerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
