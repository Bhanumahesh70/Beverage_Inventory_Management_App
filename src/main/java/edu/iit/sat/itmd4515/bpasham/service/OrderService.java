/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import jakarta.ejb.Stateless;
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
}
