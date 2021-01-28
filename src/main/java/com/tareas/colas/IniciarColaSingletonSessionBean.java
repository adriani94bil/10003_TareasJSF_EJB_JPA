/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.colas;

import javax.ejb.Singleton;
import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(
        name="java:app/jms/colaNuevasTareas",
        interfaceName="javax.jms.Queue",
        destinationName="colasNuevasTareas"
)

@JMSConnectionFactoryDefinition(
        name="java:app/jms/TareasConnectionFactory"
)

@Singleton
public class IniciarColaSingletonSessionBean {

    // Genero un singleton para que despliegue las propiedades de JMS y luego lo leo con NuevasTareastimer SssinonBean
}
