/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.domain.Customer;
import edu.iit.sat.itmd4515.bpasham.domain.OrderBeverageDetail;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.domain.BeverageType;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import edu.iit.sat.itmd4515.bpasham.service.BeverageService;
import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import edu.iit.sat.itmd4515.bpasham.service.OrderService;
import edu.iit.sat.itmd4515.bpasham.service.SupplierService;
import edu.iit.sat.itmd4515.bpasham.web.CustomerWelcomeController;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    private static final Logger LOG = Logger.getLogger(PlaceOrderController.class.getName());
    @EJB
    private OrderService orderService;

    @EJB
    private BeverageService beverageService;

    @EJB
    private SupplierService supplierService;

    @EJB
    private CustomerService customerService;

    @Inject
    CustomerWelcomeController cwc;

    private Customer selectedCustomer;
    private Supplier selectedSupplier;
    private Map<Beverage, Boolean> selectedBeverages = new HashMap<>();
    private Map<Beverage, Integer> beverageQuantities = new HashMap<>();
    private List<Customer> customers;
    private List<Supplier> suppliers;
    private List<Beverage> beverages;
    
    private Customer customer;

   


    @PostConstruct
    public void init() {
        LOG.info("Inside PlaceOrderController.postConstruct");
        // Initialize customers, suppliers, and beverages list from database
        beverages = beverageService.findAll();
        suppliers = supplierService.findAll();
        customers = customerService.findAll();
        customer = cwc.getCustomer();
    }

    @Transactional
    public String placeOrder() {
        LOG.info("Starting to place an order");
        try {
            Order order = new Order();
            order.setOrderDate(LocalDate.now());
            order.setCustomer(customer);
            order.setSupplier(selectedSupplier);

            int totalQuantity = 0;
            List<OrderBeverageDetail> details = new ArrayList<>();
            for (Beverage b : selectedBeverages.keySet()) {
                if (selectedBeverages.get(b)) {
                    int quantity = beverageQuantities.get(b);
                    totalQuantity += quantity;
                    OrderBeverageDetail detail = new OrderBeverageDetail();
                    detail.setOrder(order);
                    detail.setBeverage(b);
                    detail.setQuantity(quantity);
                    details.add(detail);
                }
            }
            order.setQuantity(totalQuantity);
            order.setOrderBeverageDetails(details);

            boolean isOrderCreated = orderService.createOrder(order);
            if (isOrderCreated) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Order successfully placed."));
                return "orderConfirmation"; // Navigate to confirmation page
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure", "Order could not be placed"));
                return null; // Stay on the same page
            }
        } catch (Exception e) {
            LOG.info("Error placing order: {}"+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error placing order", e.getMessage()));
            return null; // Stay on the same page
        }
    }

    
     /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // Customer-related fields
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

// Supplier-related fields
    public Supplier getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(Supplier selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

// Beverage-related fields
    public Map<Beverage, Boolean> getSelectedBeverages() {
        return selectedBeverages;
    }

    public void setSelectedBeverages(Map<Beverage, Boolean> selectedBeverages) {
        this.selectedBeverages = selectedBeverages;
    }

    public Map<Beverage, Integer> getBeverageQuantities() {
        return beverageQuantities;
    }

    public void setBeverageQuantities(Map<Beverage, Integer> beverageQuantities) {
        this.beverageQuantities = beverageQuantities;
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

}
