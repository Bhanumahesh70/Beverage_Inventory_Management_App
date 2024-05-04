/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author bhanu
 * @param <T>
 */
public abstract class AbstractService<T> {

    /**
     *EntityManager
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    
    /**
     *entityClass
     */
    protected Class<T> entityClass;

    /**
     *
     * @param entityClass
     */
    protected AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    /**
     *Method to create an entity
     * @param entity
     */
    public void create(T entity){
        em.persist(entity);
    }

    /**
     *Method to read an entity
     * @param id
     * @return
     */
    public T read(long id){
        return em.find(entityClass,id);
    }

    /**
     *Method to update an entity
     * @param entity
     */
    public void update(T entity){
        
        em.merge(entity);
    }

    /**
     *Method to delete an entity
     * @param entity
     */
    public void delete(T entity){
        
        em.remove(em.merge(entity));
    }
    
    /**
     *Method to find all available entities
     * @param namedQueryName
     * @return
     */
    protected List<T> findAll(String namedQueryName){
        
        return em.createNamedQuery(namedQueryName,entityClass).getResultList();
    }
}
