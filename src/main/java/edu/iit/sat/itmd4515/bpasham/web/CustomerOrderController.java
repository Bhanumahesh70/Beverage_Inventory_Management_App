/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import edu.iit.sat.itmd4515.bpasham.service.OrderService;
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
public class CustomerOrderController {
    
      private static final Logger LOG = Logger.getLogger(SupplierBeverageController.class.getName());
      
      @EJB OrderService orderSvc;
    @EJB CustomerService customerSvc;
    @Inject CustomerWelcomeController cwc;
    
    private Order order;

    public CustomerOrderController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        order = new Order();
        LOG.info("CustomerOrderController.postConstruct");
    }
    
    //action method
    
}
