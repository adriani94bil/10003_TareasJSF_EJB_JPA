/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareas.servicios;

import com.tareas.entidades.Usuario;
import com.tareas.excepciones.UsuarioNotFoundException;
import com.tareas.excepciones.UsuarioUpdateException;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class UsuarioService implements UsuarioServiceLocal {

    @PersistenceContext(unitName="TareasPU")
    private EntityManager em;

    @Override
    public Usuario getUsuario(int id) throws UsuarioNotFoundException{
        Usuario u=em.find(Usuario.class, id);
        if (u==null) {
            throw new UsuarioNotFoundException("No existe el usuario solicitado");
        }
        return u;
    }

    @Override
    public void alta(Usuario usr) {
        em.persist(usr);
    }

    @Override
    public Collection<Usuario> getAllUsuarios() {
        //OJO jpa query SELECT u FROM Usuario u 
//        String consulta="SELECT u FROM Usuario u";
        Query query=em.createNamedQuery("Usuario.findAll");
        return query.getResultList();
    }

    @Override
    public Usuario getUsuarioByEmail(String email) throws UsuarioNotFoundException{
        Query query=em.createNamedQuery("Usuario.findByEmail");
        query.setParameter("email", email);
        try{
            Usuario usr=(Usuario) query.getSingleResult();
            return usr;
        } catch(javax.persistence.NoResultException e){
            throw new UsuarioNotFoundException("no existe un usuario con el email");
        }
    }

    @Override
    public void borrar(int i) throws UsuarioNotFoundException {
        Usuario u=this.getUsuario(i);
        em.remove(u);
    }

    @Override
    public void modificar(Usuario usr) throws UsuarioNotFoundException, UsuarioUpdateException {
        Usuario usrBD= this.getUsuario(usr.getIdUsuario());
//        usrBD.setEmail(usr.getEmail());
//        usrBD.setNombre(usr.getNombre());
//        usrBD.setPassword(usr.getPassword());
//      Los métodos de EJB hace commit al final si no hay excepciones
//      Commit --> hace que se sincronice los objetos de em con las tablas de la bd
        em.merge(usr);
    }
    
    
}
