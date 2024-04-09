/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
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
    public CustomerService() {
        super(Customer.class);
    }
    
    
    public List<Customer> findAll(){
        return super.findAll("Customer.findAll");
    }
    
    public Customer findByUsername(String username){
        
        return em.createNamedQuery("Customer.findByUsername",Customer.class).setParameter("uname", username).getSingleResult();
    }
}
