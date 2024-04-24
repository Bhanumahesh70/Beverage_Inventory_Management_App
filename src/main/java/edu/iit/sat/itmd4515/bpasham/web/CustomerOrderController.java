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
    
      private static final Logger LOG = Logger.getLogger(CustomerOrderController.class.getName());
      
    @EJB OrderService orderSvc;
    @EJB CustomerService customerSvc;
    @Inject CustomerWelcomeController cwc;
    
        private Order order;

    


    public CustomerOrderController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        order = new Order();
        LOG.info("Inside CustomerOrderController.postConstruct");
    }
    
    //action method
    public String displayViewOrderPage(Order o){
        //step-1
        this.order = o;
        LOG.info("Inside displayViewOrderPage with model "+o.toString());
        
        //step-2
        return "/customer/viewOrder.xhtml";
    }
    public String displayEditOrderPage(Order o){
        //step-1
         this.order = o;
        LOG.info("Inside displayEditOrderPage with model "+o.toString());
        
        //step-2
        return "/customer/editOrder.xhtml";
    }
    public String displayDeleteOrderPage(Order o){
        //step-1
        this.order = o;
        LOG.info("Inside displayDeleteOrderPage with model "+o.toString());
        
        //step-2
        return "/customer/deleteOrder.xhtml";
    }
    
    /**
     * These are the MVC style step 3 methods
     * If there was an action associated with JSF view step2 one of these methods would be action of the form
     * or composite component on that page
     * In other words these methods handle the action click
     * @return 
     */
     public String saveOrder(){
        LOG.info("saveOrder has been invoked with model: " + this.order.toString());
        
        //beverageSvc.create(beverage);
        customerSvc.createOrderForCustomer(cwc.getCustomer(),order);
        LOG.info("saveOrder after calling service layer: " + this.order.toString());
        cwc.refreshCustomerModel();
         return "/customer/welcome.xhtml";
    }
    public String editOrder(){
        LOG.info("editOrder has been invoked with model: " + this.order.toString());
        orderSvc.editOrderForExistingCustomer(order);
        cwc.refreshCustomerModel();
        return "/customer/welcome.xhtml";
    }
    public String deleteOrder(){
        LOG.info("deleteOrder has been invoked with model: " + this.order.toString());
        return "/customer/welcome.xhtml";
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
