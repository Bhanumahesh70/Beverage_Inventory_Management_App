/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.service.SupplierService;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class SupplierWelcomeController {

    private static final Logger LOG = Logger.getLogger(SupplierWelcomeController.class.getName());

    @EJB SupplierService  supplierSvc;
    @Inject LoginController loginController;
    
    //the model
    private Supplier supplier;

    /**
     *
     */
    public SupplierWelcomeController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        
        LOG.info("SupplierWelcomeController.postConstruct");
        //initialize our model, not with a new supplier, but with finding the supplier correlated with the currently authenticated user
        
        supplier = supplierSvc.findByUsername(loginController.getAuthenticatedUser());
         LOG.info("SupplierWelcomeController.postConstruct: "+ supplier.toString());
    }
    
    //utility or helper methods

    /**
     *
     */
    public void refreshSupplierModel(){
        LOG.info("SupplierWelcomeController.refreshSupplierModel");
        supplier = supplierSvc.findByUsername(loginController.getAuthenticatedUser());
    }
    /**
     * Get the value of supplier
     *
     * @return the value of supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Set the value of supplier
     *
     * @param supplier new value of supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
