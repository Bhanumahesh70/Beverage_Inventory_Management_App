/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.domain.OrderBeverageDetail;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author bhanu
 */
@Named
@Stateless
public class BeverageService extends AbstractService<Beverage>{

    /**
     *No arg constructor
     */
    public BeverageService() {
        super(Beverage.class);
    }
    
    /**
     * constructor
     * @return
     */
    public List<Beverage> findAll(){
        return super.findAll("Beverage.findAll");
    }
    
    /**
     *Method to edit a beverage for a supplier
     * @param b
     */
    public void editBeverageForExistingSupplier(Beverage b){
       /**
        * step-1 make sure we have a managed entity to work with
        */
       Beverage managedRef = em.getReference(Beverage.class, b.getId());
       /**
        * step-2 the method parameter contains the fields that might be updated
        * we know what fields we allow to be updated via the form
        * as a developer,we are in control of that
        */
       managedRef.setName(b.getName());
       managedRef.setExpiryDate(b.getExpiryDate());
       managedRef.setIsNonAlcoholic(b.getIsNonAlcoholic());
       managedRef.setType(b.getType());
       
       /**
        * step-3 use em.merge on the managedRef, not on the parameter
        */
       em.merge(managedRef);
   }
    
    /**
     *Method to mark an beverage as delete or non delete by supplier 
     * so it can be hidden or shown to the customer
     * @param b
     */
    public void markBeverageAsDeletedNonDeleted(Beverage b) {
        Beverage beverage = em.find(Beverage.class, b.getId());
        if (beverage != null) {
            if(!beverage.isIsDeleted()){
                beverage.setDeleted(true);
            }else{
                beverage.setDeleted(false);
            }
            
            em.merge(beverage);
        } else {
            throw new IllegalArgumentException("Beverage with ID " +b.getId()+ " not found");
        }
    }

    /**
     *Method to find all non deleted beverages
     * to be shown to customer
     * @return
     */
    public List<Beverage> findAllNonDeleted() {
        return em.createQuery("SELECT b FROM Beverage b WHERE b.isDeleted = false", Beverage.class)
                 .getResultList();
    }
    
    
}
