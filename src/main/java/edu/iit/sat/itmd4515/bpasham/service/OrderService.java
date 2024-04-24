/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import jakarta.ejb.Stateless;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author bhanu
 */
@Stateless
public class OrderService extends AbstractService<Order>{
    public OrderService() {
        super(Order.class);
    }
    
    
    public List<Order> findAll(){
        return super.findAll("Order.findAll");
    }
    /*
    public void placeNewOrder(Order order){ 
        order.setOrderDate(LocalDate.now());
        
        Order newOrder = new Order(order.getOrderDate(),order.getQuantity());
        
        newOrder.placeOrder(
                //em.getReference(Customer.class, order.getCustomer().getId()),
                //em.getReference(Beverage.class, order.getBeverages().),
               // em.getReference(Supplier.class, order.getSupplier().getId()));
    }
    */
    public void editOrderForExistingCustomer(Order o){
       /**
        * step-1 make sure we have a managed entity to work with
        */
       Order managedRef = em.getReference(Order.class, o.getId());
       /**
        * step-2 the method parameter contains the fields that might be updated
        * we know what fields we allow to be updated via the form
        * as a developer,we are in control of that
        */
       managedRef.setOrderDate(o.getOrderDate());
       managedRef.setQuantity(o.getQuantity());
       /**
        * step-3 use em.merge on the managedRef, not on the parameter
        */
       em.merge(managedRef);
   }
}
