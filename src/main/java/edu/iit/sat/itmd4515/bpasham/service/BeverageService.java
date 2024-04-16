/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
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

    public BeverageService() {
        super(Beverage.class);
    }
    
    
    public List<Beverage> findAll(){
        return super.findAll("Beverage.findAll");
    }
    
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
    
}
