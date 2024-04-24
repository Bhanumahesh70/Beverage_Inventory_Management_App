/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.service.BeverageService;
import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import edu.iit.sat.itmd4515.bpasham.service.OrderService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class PlaceOrderController {

    @EJB
    private OrderService orderService;

    @EJB
    private BeverageService beverageService;

    @EJB
    private CustomerService customerService;

    @Inject
    CustomerWelcomeController cwc;

    
    private Map<Beverage, Integer> quantityMap;

    private List<Beverage> availableBeverages;

    private static final Logger LOG = Logger.getLogger(PlaceOrderController.class.getName());

    @PostConstruct
    public void postConstruct() {

        LOG.info("In PlaceOrderController.postConstruct");
        
        //Initializing the model
       
        
        
        quantityMap = new HashMap<>();
        availableBeverages = beverageService.findAll();
        for (Beverage beverage : availableBeverages) {
            quantityMap.put(beverage, 0); // Initialize quantities to 0
        }
    }

    public String placeOrder() {
     Order order = new Order();
    order.setOrderDate(LocalDate.now());
    for (Map.Entry<Beverage, Integer> entry : quantityMap.entrySet()) {
        Beverage beverage = entry.getKey();
        Integer quantity = null;
        try {
           // quantity = Integer.valueOf(entry.getValue().toString());
           quantity = entry.getValue();
        } catch (NumberFormatException e) {
            LOG.info("Error in placeOrder");
        }
        if (quantity != null && quantity > 0) {
            
            LOG.info("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii selected beverage is: "+beverage.toString());
            order.addBeverage(beverage);
            order.setQuantity(quantity);
        }
    }
    // Save the order
    // orderService.placeOrder(order);
    LOG.log(Level.INFO, "saveOrder has been invoked with model: {0}", order.toString());

    // beverageSvc.create(beverage);
    customerService.createOrderForCustomer(cwc.getCustomer(), order);
    LOG.info("saveOrder after calling service layer: " + order.toString());
    cwc.refreshCustomerModel();
    return "/customer/welcome.xhtml";
}

    /**
     * Get the value of availableBeverages
     *
     * @return the value of availableBeverages
     */
    public List<Beverage> getAvailableBeverages() {
        return availableBeverages;
    }

    /**
     * Set the value of availableBeverages
     *
     * @param availableBeverages new value of availableBeverages
     */
    public void setAvailableBeverages(List<Beverage> availableBeverages) {
        this.availableBeverages = availableBeverages;
    }

    /**
     * Get the value of quantityMap
     *
     * @return the value of quantityMap
     */
    public Map<Beverage, Integer> getQuantityMap() {
        return quantityMap;
    }

    /**
     * Set the value of quantityMap
     *
     * @param quantityMap new value of quantityMap
     */
    public void setQuantityMap(Map<Beverage, Integer> quantityMap) {
        this.quantityMap = quantityMap;
    }
}
