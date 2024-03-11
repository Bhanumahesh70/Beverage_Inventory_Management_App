/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * @author bhanu
 */
@Stateless
public class SupplierService {
    
    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;
    
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
    }
}
