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
import jakarta.transaction.Transactional;
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

    @EJB
    OrderService orderSvc;
    @EJB
    CustomerService customerSvc;
    @Inject
    CustomerWelcomeController cwc;

    private Order order;
    private List<OrderBeverageDetail> orderDetails;

    /**
     *
     */
    public CustomerOrderController() {
    }

    @PostConstruct
    private void postConstruct() {
        order = new Order();
        orderDetails = new ArrayList<>();
        LOG.info("Inside CustomerOrderController.postConstruct");
    }

    /**
     *MEthod to add beverage to order
     */
    public void addBeverageToOrder() {
        OrderBeverageDetail detail = new OrderBeverageDetail();
        detail.setOrder(order);
        detail.setQuantity(1); // Default quantity
        orderDetails.add(detail);
    }

    /**
     *MEthod to remove beverage from order
     * @param detail
     */
    public void removeBeverage(OrderBeverageDetail detail) {
        orderDetails.remove(detail);
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
