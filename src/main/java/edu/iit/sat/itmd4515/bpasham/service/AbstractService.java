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
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    
    /**
     *
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
     *
     * @param entity
     */
    public void create(T entity){
        em.persist(entity);
    }

    /**
     *
     * @param id
     * @return
     */
    public T read(long id){
        return em.find(entityClass,id);
    }

    /**
     *
     * @param entity
     */
    public void update(T entity){
        
        em.merge(entity);
    }

    /**
     *
     * @param entity
     */
    public void delete(T entity){
        
        em.remove(em.merge(entity));
    }
    
    /**
     *
     * @param namedQueryName
     * @return
     */
    protected List<T> findAll(String namedQueryName){
        
        return em.createNamedQuery(namedQueryName,entityClass).getResultList();
    }
}
