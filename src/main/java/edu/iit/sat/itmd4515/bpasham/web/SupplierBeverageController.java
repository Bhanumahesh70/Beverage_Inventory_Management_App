/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.service.BeverageService;
import edu.iit.sat.itmd4515.bpasham.service.SupplierService;
import edu.iit.sat.itmd4515.bpasham.domain.BeverageType;
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
public class SupplierBeverageController {

    private static final Logger LOG = Logger.getLogger(SupplierBeverageController.class.getName());
    
    @EJB BeverageService beverageSvc;
    @EJB SupplierService supplierSvc;
    @Inject SupplierWelcomeController swc;
    

    private Beverage beverage;
    
    private Order order;

   
    
    public SupplierBeverageController() {
    }
    
    
    @PostConstruct
    private void postConstruct(){
        beverage= new Beverage();
        LOG.info("SupplierBeverageController.postConstruct");
    }
    
    //action method
    /**
     * MVC Style Naviation Method for viewing a beverage in read-only mode
     *Step-1 Accepting the click and set the model, with any associated parameter to the action method
     *step-2 Navigating the user to the appropriate JS view to complete their operation
     * Step-3 If applicable,is invoking the action/application (in JS life cycle terminology),
     * and likely return the user some other view as final result
     * This is would effectively mean handling the click from the JSF view in step 2, in order to perform the operation
     * the user is trying to accomplish
     * 
     */
    public String displayViewBeveragePage(Beverage b){
        //step-1
        this.beverage = b;
        LOG.info("Inside displayViewBeveragePage with model "+b.toString());
        
        //step-2
        return "/supplier/viewBeverage.xhtml";
    }
    public String displayEditBeveragePage(Beverage b){
        //step-1
        this.beverage = b;
        LOG.info("Inside displayEditBeveragePage with model "+b.toString());
        
        //step-2
        return "/supplier/editBeverage.xhtml";
    }
    public String displayDeleteBeveragePage(Beverage b){
        //step-1
        this.beverage = b;
        LOG.info("Inside displayDeleteBeveragePage with model "+b.toString());
        
        //step-2
        return "/supplier/deleteBeverage.xhtml";
    }
     public String displayViewOrderPage(Order o){
        //step-1
        this.order = o;
        LOG.info("Inside SupplierBeveradisplayViewOrderPage with model "+o.toString());
        
        //step-2
        return "/supplier/viewOrder.xhtml";
    }
    
    /**
     * These are the MVC style step 3 methods
     * If there was an action associated with JSF view step2 one of these methods would be action of the form
     * or composite componentent on that page
     * In other words these methods handle the action click
     * @return 
     */
     public String saveBeverage(){
        LOG.info("saveBeverage has been invoked with model: " + this.beverage.toString());
        
        //beverageSvc.create(beverage);
        supplierSvc.createBeverageForSupplier(swc.getSupplier(),beverage);
        LOG.info("saveBeverage after calling service layer: " + this.beverage.toString());
        swc.refreshSupplierModel();
         return "/supplier/welcome.xhtml";
    }
    public String editBeverage(){
        LOG.info("editBeverage has been invoked with model: " + this.beverage.toString());
        beverageSvc.editBeverageForExistingSupplier(beverage);
        swc.refreshSupplierModel();
        return "/supplier/welcome.xhtml";
    }
    public String deleteBeverage(){
        LOG.info("deleteBeverage has been invoked with model: " + this.beverage.toString());
        beverageSvc.markBeverageAsDeletedNonDeleted(beverage);
        swc.refreshSupplierModel();
        return "/supplier/welcome.xhtml";
    }
    
    //helper methods
    public BeverageType[] getAllBeverageTypesForForm(){
        return BeverageType.values();                         
    }
       

    /**
     * Get the value of beverage
     *
     * @return the value of beverage
     */
    public Beverage getBeverage() {
        return beverage;
    }

    /**
     * Set the value of beverage
     *
     * @param beverage new value of beverage
     */
    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }
 /**
     * Get the value of order
     *
     * @return the value of order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Set the value of order
     *
     * @param order new value of order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

}
