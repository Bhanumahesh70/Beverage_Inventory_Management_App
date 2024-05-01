/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.domain.OrderBeverageDetail;
import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import edu.iit.sat.itmd4515.bpasham.service.OrderService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.lang.System.Logger.Level;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
            private List<OrderBeverageDetail> orderDetails;


    


    public CustomerOrderController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        order = new Order();
        orderDetails = new ArrayList<>();
        LOG.info("Inside CustomerOrderController.postConstruct");
    }
    
    //action method
    public String displayViewOrderPage(Order o){
        //step-1
        this.order = o;
        LOG.info("Inside displayViewOrderPage with model "+o.toString());
        LOG.log(java.util.logging.Level.INFO, "Displaying Beverages {0}", o.getBeverages());
        LOG.info("Displaying orderBeverageDetails "+ o.getOrderBeverageDetails());
        
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
    
    public String createOrder() {
        order.setOrderDate(LocalDate.now());
        //orderSvc.createOrder(order,orderDetails);
        cwc.refreshCustomerModel();
        return "/customer/welcome.xhtml?faces-redirect=true";
    }

    public String editOrder() {
      //  orderSvc.updateOrder(order);
        cwc.refreshCustomerModel();
        return "/customer/welcome.xhtml?faces-redirect=true";
    }
    public String updateOrder() {
    
    LOG.info("Inside PlaceOrderController.updateOrder");
    LOG.log(java.util.logging.Level.INFO, "Updating order with ID: '{'0'}'{0}", this.order.getId());

    if (this.order == null || this.order.getId() == null) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid order data."));
        return null; // Stay on the same page to correct data
    }
    try {
        // Validate the order details if necessary
        orderSvc.updateOrder(this.order, this.order.getOrderBeverageDetails()); // Assume an update method in your service
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Order successfully updated."));
        return "orderUpdatedConfirmation"; // Navigate to confirmation page
    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating order", e.getMessage()));
        LOG.info(e.getMessage());
        return null; // Stay on the same page
    }
}


    public String deleteOrder() {
        orderSvc.deleteOrder(order.getId());
        cwc.refreshCustomerModel();
        return "/customer/welcome.xhtml?faces-redirect=true";
    }
    public void addBeverageToOrder() {
        OrderBeverageDetail detail = new OrderBeverageDetail();
        detail.setOrder(order);
        detail.setQuantity(1); // Default quantity
        orderDetails.add(detail);
    }

    public void removeBeverage(OrderBeverageDetail detail) {
        orderDetails.remove(detail);
    }
    
    /*commenting below methods
    *
    *
    */
    /*
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
    */
    
    
    
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
    /**
     * Get the value of orderDetails
     *
     * @return the value of orderDetails
     */
    public List<OrderBeverageDetail> getOrderDetails() {
        return orderDetails;
    }

    /**
     * Set the value of orderDetails
     *
     * @param orderDetails new value of orderDetails
     */
    public void setOrderDetails(List<OrderBeverageDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
