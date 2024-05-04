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

    /**
     *
     */
    protected Class<Supplier> entityClass;
    
    /**
     *No arg constructor
     */
    public SupplierService(){
        
    }
    
    /**
     *Method to create a supplier to database
     * @param s
     */
    public void create(Supplier s){
        em.persist(s);
    }

    /**
     *Method to read a supplier from database
     * @param id
     * @return
     */
    public Supplier read(long id){
        return em.find(Supplier.class,id);
    }

    /**
     *Method to update a supplier from database
     * @param s
     */
    public void update(Supplier s){
        
        em.merge(s);
    }

    /**
     *Method to delete a supplier from database
     * @param s
     */
    public void delete(Supplier s){
        
        em.remove(em.merge(s));
    }
    
    /**
     *Method to find all available suppliers
     * @return
     */
    public List<Supplier> findAll(){
        
        return em.createNamedQuery("Supplier.findAll",Supplier.class).getResultList();
         //return em.createNamedQuery(namedQueryName,entityClass).getResultList();
    }
    
    /**
     *Method to find supplier by username
     * @param username
     * @return
     */
    public Supplier findByUsername(String username){
        
        return em.createNamedQuery("Supplier.findByUsername",Supplier.class).setParameter("uname", username).getSingleResult();
    }

    /**
     *Method to create a Beverage by supplier
     * @param supplier
     * @param beverage
     */
    public void createBeverageForSupplier(Supplier supplier, Beverage beverage) {
        //just like database initializer
        //create non-owing entities first
        
        em.persist(beverage);
        
        //we can also do this with em.merge, but we need to be careful because merge
        //will als update the database if anythin is different in parameter
        //this is a little safer
        Supplier supplierRef= em.getReference(Supplier.class, supplier.getId());
        supplierRef.addBeverage(beverage);
        em.merge(supplierRef);
  }
    
  
}
