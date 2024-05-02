/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.domain.OrderBeverageDetail;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import edu.iit.sat.itmd4515.bpasham.domain.Customer;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.web.PlaceOrderController;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Stateless
public class OrderService extends AbstractService<Order> {
private static final Logger LOG = Logger.getLogger(OrderService.class.getName());
    public OrderService() {
        super(Order.class);
    }

    public List<Order> findAll() {
        return super.findAll("Order.findAll");
    }

    @Transactional
    public boolean createOrder(Order order) {
        try {
            Customer customer = em.find(Customer.class, order.getCustomer().getId());
            Supplier supplier = em.find(Supplier.class, order.getSupplier().getId());
            if (customer == null || supplier == null) {
                throw new IllegalArgumentException("Customer or Supplier not found");
            }

            order.setCustomer(customer);
            order.setSupplier(supplier);

            List<OrderBeverageDetail> newDetails = new ArrayList<>();
            for (OrderBeverageDetail detail : order.getOrderBeverageDetails()) {
                Beverage beverage = em.find(Beverage.class, detail.getBeverage().getId());
                if (beverage == null) {
                    LOG.info("Beverage not found with ID: {}"+detail.getBeverage().getId());
                    continue; // Skipping this detail
                }
                detail.setBeverage(beverage);
                newDetails.add(detail);
                em.persist(detail);
            }
            order.getOrderBeverageDetails().clear();
            order.getOrderBeverageDetails().addAll(newDetails);
            LOG.info("OrderService.CreateOrder: Order Before Persist");
            LOG.info("Order before Persists: "+order.toString());
            LOG.info("Oder OrderBevergaeDetail: "+order.getOrderBeverageDetails().toString());
            LOG.info("getOrderBeverageDetailstoString()"+order.getOrderBeverageDetailstoString());
            em.persist(order);
            return true;
        } catch (Exception e) {
            LOG.info("Error creating order: {}"+ e.getMessage());
            return false;
        }
    }
    /*
    public void updateOrder(Order order) {
        if (order == null || order.getId() == null) {
            throw new IllegalArgumentException("Order or Order ID must not be null");
        }
        Order managedOrder = em.getReference(Order.class, order.getId());
        if (managedOrder == null) {
            throw new EntityNotFoundException("Order with ID " + order.getId() + " not found");
        }
        managedOrder.setOrderDate(order.getOrderDate());
        managedOrder.setQuantity(order.getQuantity());

        // Clear existing details to replace with the updated list
        managedOrder.getOrderBeverageDetails().clear();
        em.flush();

        for (OrderBeverageDetail detail : order.getOrderBeverageDetails()) {
            detail.setOrder(managedOrder);
            Beverage beverage = em.find(Beverage.class, detail.getBeverage().getId());
            detail.setBeverage(beverage);
            managedOrder.getOrderBeverageDetails().add(detail);
            em.persist(detail);
        }

        em.merge(managedOrder);
    }
    */
    
 @Transactional
    public boolean updateOrder(Order order) {
        LOG.info("Inside OrderService.updateOrder");
        if (order == null || order.getId() == null) {
            throw new IllegalArgumentException("Order or Order ID must not be null");
        }
        try {
            Order managedOrder = em.getReference(Order.class, order.getId());
            if (managedOrder == null) {
                throw new EntityNotFoundException("Order with ID " + order.getId() + " not found");
            }

            managedOrder.setSupplier(em.getReference(Supplier.class, order.getSupplier().getId())); // Update supplier reference

            managedOrder.getOrderBeverageDetails().clear(); // Clear existing details
            List<OrderBeverageDetail> newDetails = new ArrayList<>();
            for (OrderBeverageDetail detail : order.getOrderBeverageDetails()) {
                Beverage beverage = em.getReference(Beverage.class, detail.getBeverage().getId());
                if (beverage == null) {
                    LOG.info("Beverage not found with ID: {}"+ detail.getBeverage().getId());
                    continue; // Skipping this detail
                }
                if (beverage != null) {
                    detail.setOrder(managedOrder);
                    detail.setBeverage(beverage);
                    newDetails.add(detail);
                    em.persist(newDetails);
                }
            }
            managedOrder.getOrderBeverageDetails().addAll(newDetails);
            LOG.info("Order before mergin: "+managedOrder.toString());
            LOG.info("Oder OrderBevergaeDetail: "+managedOrder.getOrderBeverageDetails().toString());
            LOG.info("getOrderBeverageDetailstoString()"+managedOrder.getOrderBeverageDetailstoString());
            em.merge(managedOrder);
            return true;
        } catch (Exception e) {
            LOG.info("Error updating order: {}"+ e.getMessage());
            return false;
        }
    }

    public void deleteOrder(Long orderId) {
        Order orderToDelete = em.find(Order.class, orderId);
        if (orderToDelete != null) {
            em.remove(orderToDelete);
        }
    }

    ///////////////////////
    /////////////////////////
    //////////////
    public void placeNewOrder(Order order, List<Beverage> selectedBeverages) {
        // Create a new order entity
        Order newOrder = new Order(order.getOrderDate(), order.getQuantity());
        // Set the customer and supplier
        newOrder.setCustomer(em.getReference(Customer.class, order.getCustomer().getId()));
        newOrder.setSupplier(em.getReference(Supplier.class, order.getSupplier().getId()));

        // Set the selected beverages
        newOrder.setBeverages(selectedBeverages);
        // Persist the new order
        em.persist(newOrder);
    }
    /*
                    *Commenting the methods below
                    *

                    
                    /////*
                    public void placeNewOrder(Order order) {
                        Order newOrder = new Order(order.getOrderDate(), order.getQuantity());
                        newOrder.placeOrder(
                                em.getReference(Customer.class, order.getCustomer().getId()),
                                em.getReference(Beverage.class,order.getBeverages()),
                                em.getReference(Supplier.class, order.getSupplier().getId()));
                        em.persist(newOrder);
                    }
                     ////*88888
                    

                    public void editOrderForExistingCustomer(Order o) {
                        /**
                         * step-1 make sure we have a managed entity to work with
                         /88888
                        Order managedRef = em.getReference(Order.class, o.getId());
                        /**
                         * step-2 the method parameter contains the fields that might be updated
                         * we know what fields we allow to be updated via the form as a
                         * developer,we are in control of that
                         /8888
                        managedRef.setOrderDate(o.getOrderDate());
                        managedRef.setQuantity(o.getQuantity());
                        /**
                         * step-3 use em.merge on the managedRef, not on the parameter
                         /88888
                        em.merge(managedRef);
                    }
                    
     */

}
