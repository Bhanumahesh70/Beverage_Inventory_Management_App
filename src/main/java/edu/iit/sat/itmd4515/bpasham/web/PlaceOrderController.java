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

        private Order order;
        
    private List<OrderBeverageDetail> orderBeverageDetail;

    
        

    

   //private Order order;
   //List<OrderBeverageDetail> details = new ArrayList<>();


    @PostConstruct
    public void init() {
        LOG.info("Inside PlaceOrderController.postConstruct");
        // Initialize customers, suppliers, and beverages list from database
        beverages = beverageService.findAll();
        suppliers = supplierService.findAll();
        customers = customerService.findAll();
        customer = cwc.getCustomer();
    }
public String displayViewOrderPage(Order o) {
        //step-1
        this.order = o;
        LOG.info("Inside PlaceOrderController.displayEditOrderPage with model " + o.toString());
        LOG.info("Order in displayEditOrderPage: " + order.toString());
        LOG.log(java.util.logging.Level.INFO, "Oder OrderBeverageDetails: {0}", order.getOrderBeverageDetails().toString());
        LOG.log(java.util.logging.Level.INFO, "Oder(o) OrderBeverageDetails: {0}", o.getOrderBeverageDetails().toString());
        LOG.info("Order.getCustomer(): "+order.getCustomer());
        //LOG.info("customer: "+customer);
        LOG.info("Order.supplier: "+order.getSupplier());
        
        LOG.info("Inside displayViewOrderPage with model " + o.toString());
        LOG.log(java.util.logging.Level.INFO, "Displaying Beverages {0}", o.getBeverages());
        LOG.info("Displaying orderBeverageDetails " + o.getOrderBeverageDetails());

        //step-2
        return "/customer/viewOrder.xhtml";
    }

    public String displayEditOrderPage(Order o) {
        //step-1
        this.order = o;

        LOG.info("Inside PlaceOrderController.displayEditOrderPage with model " + o.toString());
        LOG.info("Order in displayEditOrderPage: " + order.toString());
        LOG.log(java.util.logging.Level.INFO, "Oder OrderBevergaeDetail: {0}", order.getOrderBeverageDetails().toString());
        LOG.info("Order.getCustomer(): "+order.getCustomer());
        LOG.info("customer: "+customer);
        LOG.info("Order.supplier: "+order.getSupplier());
        
        

        //step-2
        return "/customer/editOrder.xhtml";
    }
    @Transactional
    public String placeOrder() {
        LOG.info("Starting to place an order");
        try {
            order = new Order();
            order.setOrderDate(LocalDate.now());
            order.setCustomer(customer);
            order.setSupplier(selectedSupplier);

            int totalQuantity = 0;
           orderBeverageDetail = new ArrayList<>();
            for (Beverage b : selectedBeverages.keySet()) {
                if (selectedBeverages.get(b)) {
                    int quantity = beverageQuantities.get(b);
                    totalQuantity += quantity;
                    OrderBeverageDetail detail = new OrderBeverageDetail();
                    detail.setOrder(order);
                    detail.setBeverage(b);
                    detail.setQuantity(quantity);
                    orderBeverageDetail.add(detail);
                }
            }
            order.setQuantity(totalQuantity);
            order.setOrderBeverageDetails(orderBeverageDetail);

            boolean isOrderCreated = orderService.createOrder(order);
            if (isOrderCreated) {
                LOG.info("Order is created Successfully");
                LOG.info("PlaceOrderontroller.placeOrder: Order After Persist");
            LOG.info("Order after Persists: "+order.toString());
            LOG.info("Oder OrderBevergaeDetail: "+order.getOrderBeverageDetails().toString());
            LOG.info("getOrderBeverageDetailstoString()"+order.getOrderBeverageDetailstoString());
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
@Transactional
    public String updateOrder() {
        LOG.info("Inside CustomerOrderController.updateOrder with Order ID: {}" + order.getId());
        LOG.info("Order in customerOrderController.updateOrder: " + order.toString());
        LOG.info("getOrderBeverageDetailstoString()"+order.getOrderBeverageDetailstoString());
        LOG.log(java.util.logging.Level.INFO, "Oder OrderBevergaeDetail: {0}", order.getOrderBeverageDetails().toString());

        if (order == null || order.getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid order data."));
            return null; // Stay on the same page to correct data
        }
        try {

            boolean isOrderUpdated = orderService.updateOrder(order);
            if (isOrderUpdated) {
                LOG.info("PlaceOrderController After Update");
                LOG.info("Order: "+order.toString());
                LOG.info("getOrderBeverageDetailstoString()"+order.getOrderBeverageDetailstoString());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Order successfully updated."));
                cwc.refreshCustomerModel();
                return "/customer/welcome.xhtml?faces-redirect=true";

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure", "Order could not be updated"));
                return null; // Stay on the same page
            }
        } catch (Exception e) {
            LOG.info("Error updating order: {}" + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating order", e.getMessage()));
            return null; // Stay on the same page
        }
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
    /**
     * Get the value of orderBeverageDetail
     *
     * @return the value of orderBeverageDetail
     */
    public List<OrderBeverageDetail> getOrderBeverageDetail() {
        return orderBeverageDetail;
    }

    /**
     * Set the value of orderBeverageDetail
     *
     * @param orderBeverageDetail new value of orderBeverageDetail
     */
    public void setOrderBeverageDetail(List<OrderBeverageDetail> orderBeverageDetail) {
        this.orderBeverageDetail = orderBeverageDetail;
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
