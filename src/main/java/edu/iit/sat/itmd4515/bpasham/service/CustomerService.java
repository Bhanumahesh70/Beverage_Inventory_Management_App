/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.domain.Customer;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author bhanu
 */
@Stateless
public class CustomerService extends AbstractService<Customer>{

    /**
     *
     */
    public CustomerService() {
        super(Customer.class);
    }
    
    /**
     *
     * @return
     */
    public List<Customer> findAll(){
        return super.findAll("Customer.findAll");
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Customer findByUsername(String username){
        
        return em.createNamedQuery("Customer.findByUsername",Customer.class).setParameter("uname", username).getSingleResult();
    }
    
    /**
     *
     * @param customer
     * @param order
     */
    public void createOrderForCustomer(Customer customer, Order order) {
        //just like database initializer
        //create non-owing entities first
        
        em.persist(order);
        
        //we can also do this with em.merge, but we need to be careful because merge
        //will als update the database if anythin is different in parameter
        //this is a little safer
        Customer customerRef= em.getReference(Customer.class, customer.getId());
       
        customerRef.addOrder(order);
        em.merge(customerRef);
  }
}
