/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import jakarta.annotation.PostConstruct;
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
        LOG.info("Application has been invoked with model" + this.beverage.toString());
         return "confirmation.xhtml";
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
