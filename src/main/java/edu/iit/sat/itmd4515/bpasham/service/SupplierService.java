/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;

/**
 *
 * @author bhanu
 */
@Stateless
public class SupplierService {
    
    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;
    protected Class<Supplier> entityClass;
    
    public SupplierService(){
        
    }
    
    public void create(Supplier s){
        em.persist(s);
    }
    public Supplier read(long id){
        return em.find(Supplier.class,id);
    }
    public void update(Supplier s){
        
        em.merge(s);
    }
    public void delete(Supplier s){
        
        em.remove(em.merge(s));
    }
    
    public List<Supplier> findAll(){
        
        return em.createNamedQuery("Supplier.findAll",Supplier.class).getResultList();
         //return em.createNamedQuery(namedQueryName,entityClass).getResultList();
    }
    
    public Supplier findByUsername(String username){
        
        return em.createNamedQuery("Supplier.findByUsername",Supplier.class).setParameter("uname", username).getSingleResult();
    }

    public void createBeverageForSupplier(Supplier supplier, Beverage beverage) {
        //just like database initializer
        //create non-owing entities irst
  }
}
