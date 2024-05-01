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
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author bhanu
 */
@Stateless
public class OrderService extends AbstractService<Order> {

    public OrderService() {
        super(Order.class);
    }

    public List<Order> findAll() {
        return super.findAll("Order.findAll");
    }

    public void createOrder(Order order, List<OrderBeverageDetail> details) {
        try {
            Customer customer = em.find(Customer.class, order.getCustomer().getId());
            Supplier supplier = em.find(Supplier.class, order.getSupplier().getId());
            if (customer == null || supplier == null) {
                throw new IllegalArgumentException("Customer or Supplier not found");
            }

            order.setCustomer(customer);
            order.setSupplier(supplier);

            for (OrderBeverageDetail detail : details) {
                Beverage beverage = em.find(Beverage.class, detail.getBeverage().getId());
                if (beverage == null) {
                    continue;  // or handle error
                }
                detail.setOrder(order);
                detail.setBeverage(beverage);
                order.getOrderBeverageDetails().add(detail);
                em.persist(detail);
            }

            em.persist(order);
        } catch (Exception e) {
            // Handle exception, possibly rethrow as a custom exception
            throw new RuntimeException("Error creating order", e);
        }
    }

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
