/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "ORDERS")
@NamedQuery(name="Order.findAll",query="select o from Order o")
public class Order extends AbstractEntity {
    

    @NotNull
    private LocalDate orderDate;

    @Min(1)
    private Integer quantity;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
     @ManyToMany
    @JoinTable(name = "order_beverage",
               joinColumns = @JoinColumn(name = "order_id"),
               inverseJoinColumns = @JoinColumn(name = "beverage_id"))
    private List<Beverage> beverages;
    
    public Order() {
    }

    public Order(LocalDate orderDate, Integer quantity) {
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + id + ", orderDate=" + orderDate + ", quantity=" + quantity + '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    public void addBeverage(Beverage b){
        // Check if beverages list is null, initialize it if necessary
    if (this.beverages == null) {
        this.beverages = new ArrayList<>();
    }

    if(!this.beverages.contains(b)){
        this.beverages.add(b);
    }

    // Check if b's orders list is null, initialize it if necessary
    if (b.getOrders() == null) {
        b.setOrders(new ArrayList<>());
    }

    if(!b.getOrders().contains(this)){
        b.getOrders().add(this);
    }
        
    }
    public void removeBeverage(Beverage b){
        if(!this.beverages.contains(b)){
            this.beverages.remove(b);
        }
         if(!b.getOrders().contains(this)){
            b.getOrders().remove(this);
        }
        
    }

    
}
