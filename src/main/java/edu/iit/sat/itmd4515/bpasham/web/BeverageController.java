/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.service.BeverageService;
import edu.iit.sat.itmd4515.bpasham.domain.BeverageType;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class BeverageController {

    private static final Logger LOG = Logger.getLogger(BeverageController.class.getName());
    
    @EJB BeverageService beverageSvc;

    private Beverage beverage;
    
    public BeverageController() {
    }
    
    
    @PostConstruct
    private void postConstruct(){
        beverage= new Beverage();
        LOG.info("BeverageController.postConstruct");
    }
    
    //action method
    public String demoAction(){
        LOG.info("demoAction has been invoked with model: " + this.beverage.toString());
         return "confirmation.xhtml";
    }
    
     public String saveBeverage(){
        LOG.info("saveBeverage has been invoked with model: " + this.beverage.toString());
        beverageSvc.create(beverage);
        LOG.info("saveBeverage after callin service layer: " + this.beverage.toString());
         return "confirmation.xhtml";
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

}
