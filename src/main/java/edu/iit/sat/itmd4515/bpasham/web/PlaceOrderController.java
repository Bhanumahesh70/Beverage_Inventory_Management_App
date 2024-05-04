/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.domain.Customer;
import edu.iit.sat.itmd4515.bpasham.domain.OrderBeverageDetail;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import edu.iit.sat.itmd4515.bpasham.service.BeverageService;
import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import edu.iit.sat.itmd4515.bpasham.service.OrderService;
import edu.iit.sat.itmd4515.bpasham.service.SupplierService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

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

    public PlaceOrderController() {
    }

    @PostConstruct
    public void postConstruct() {
        LOG.info("Inside PlaceOrderController.postConstruct");
        // Initialize customers, suppliers, and beverages list from database
        //beverages = beverageService.findAll();
        beverages = beverageService.findAllNonDeleted();
        suppliers = supplierService.findAll();
        customers = customerService.findAll();
        customer = cwc.getCustomer();
        order = new Order();
    }

    public String displayViewOrderPage(Order o) {
        //step-1
        this.order = o;
        LOG.info("Inside PlaceOrderController.displayEditOrderPage with model " + o.toString());
        LOG.info("Order in displayEditOrderPage: " + order.toString());
        LOG.log(java.util.logging.Level.INFO, "Oder OrderBeverageDetails: {0}", order.getOrderBeverageDetails().toString());
        LOG.log(java.util.logging.Level.INFO, "Oder(o) OrderBeverageDetails: {0}", o.getOrderBeverageDetails().toString());
        LOG.info("Order.getCustomer(): " + order.getCustomer());
        //LOG.info("customer: "+customer);
        LOG.info("Order.supplier: " + order.getSupplier());

        LOG.info("Inside displayViewOrderPage with model " + o.toString());
        LOG.log(java.util.logging.Level.INFO, "Displaying Beverages {0}", o.getBeverages());
        LOG.info("Displaying orderBeverageDetails " + o.getOrderBeverageDetails());

        //step-2
        return "/customer/viewOrder.xhtml";
    }

    

    public String placeOrder() {
        LOG.info("Starting to place an order");
        try {
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
            LOG.info("PlaceOorderController.placeOrder: orderBeverageDetail " + orderBeverageDetail);
            order.setOrderBeverageDetails(orderBeverageDetail);
            LOG.info("PlaceOorderController.placeOrder: order.orderBeverageDetail.toString(): " + order.getOrderBeverageDetailstoString());

            boolean isOrderCreated = orderService.createOrder(order);
            if (isOrderCreated) {
                LOG.info("Order is created Successfully");
                LOG.info("PlaceOrderontroller.placeOrder: Order After Persist");
                LOG.info("Order after Persists: " + order.toString());
                LOG.info("Oder OrderBevergaeDetail: " + order.getOrderBeverageDetails().toString());
                LOG.info("getOrderBeverageDetailstoString()" + order.getOrderBeverageDetailstoString());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Order successfully placed."));
                cwc.refreshCustomerModel();
                return "/customer/welcome.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure: select items and enter quantity to place an order ", "Order could not be placed"));
                return null; // Stay on the same page
            }
        } catch (Exception e) {
            LOG.info("Error placing order: {}" + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error placing order. select items and enter quantity to place an order", e.getMessage()));
            return null; // Stay on the same page
        }
    }

    public String deleteOrder(Order order) {
        //order= orderService.findOrderById(orderID);
        LOG.info("Inside PlaceOrderController.deleteOrder()");
        //LOG.info("Order: " + order.toString());
        if (order == null || order.getId() == null) {
            LOG.info("PlaceOrderController.deleteOrder() Order is null");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid order data for deletion."));
            return null; // Optionally, return to a safe page or stay on the same page
        }
        try {
            LOG.info("PlaceOrderController.deleteOrder() " + order.toString());
            orderService.deleteOrder(order.getId());
            cwc.refreshCustomerModel();
            return "/customer/welcome.xhtml?faces-redirect=true";
        } catch (Exception e) {
            LOG.info("Error deleting order: {}" + e.getMessage() + ",e: " + e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error deleting order", e.getMessage()));
            return null; // Optionally, handle the return accordingly
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
