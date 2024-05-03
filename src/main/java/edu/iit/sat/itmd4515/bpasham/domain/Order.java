/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bhanu
 */
@Entity
//@XmlRootElement
@Table(name = "ORDERS")
@NamedQuery(name = "Order.findAll", query = "select o from Order o")
public class Order extends AbstractEntity {

    @NotNull
    private LocalDate orderDate;

    @Min(1)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderBeverageDetail> orderBeverageDetails= new ArrayList<>();
    
    @ManyToMany
    @JsonbTransient
    //@XmlTransient
    @JoinTable(name = "order_beverage",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "beverage_id"))
    private List<Beverage> beverages;
    
    @JoinColumn(name = "status")
    private String status = "Placed";
    


    public Order() {
    }
    

    

    public Order(LocalDate orderDate, Integer quantity) {
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    //helper methods to manage jpa relationships
    public void placeOrder(Customer c, Beverage b, Supplier s) {
        this.customer = c;

    }

    public void addBeverage(Beverage b) {
        // Check if beverages list is null, initialize it if necessary
        if (this.beverages == null) {
            this.beverages = new ArrayList<>();
        }

        if (!this.beverages.contains(b)) {
            this.beverages.add(b);
        }

        // Check if b's orders list is null, initialize it if necessary
        if (b.getOrders() == null) {
            b.setOrders(new ArrayList<>());
        }

        if (!b.getOrders().contains(this)) {
            b.getOrders().add(this);
        }

    }

    public void removeBeverage(Beverage b) {
        if (!this.beverages.contains(b)) {
            this.beverages.remove(b);
        }
        if (!b.getOrders().contains(this)) {
            b.getOrders().remove(this);
        }

    }
    
    //helper methods to manage JPA relationships
    public void placeOrder(Customer c, List<Beverage> b, Supplier s){
        this.customer=c;
        this.beverages=b;
        this.supplier =s;
        
        //handle the bidirectional relationships
        c.addOrder(this);
        s.addOrder(this);
        
    }
    
    public void addOrderBeverageDetails(Beverage beverage, Integer quantity) {
        OrderBeverageDetail orderBeverage = new OrderBeverageDetail();
        orderBeverage.setOrder(this);
        orderBeverage.setBeverage(beverage);
        orderBeverage.setQuantity(quantity);
        orderBeverageDetails.add(orderBeverage);
        beverage.getOrderBeverageDetails().add(orderBeverage);
    }

    public void removeOrderBeverage(OrderBeverageDetail orderBeverage) {
        orderBeverageDetails.remove(orderBeverage);
        orderBeverage.getBeverage().getOrderBeverageDetails().remove(orderBeverage);
        orderBeverage.setOrder(null);
        orderBeverage.setBeverage(null);
    }

    //Getter and Setter Methods
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        // Only set the customer if it's different from the current one
        if (this.customer != customer) {
            this.customer = customer;
            if (customer != null) {
                customer.addOrder(this);
            }
        }

    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    /**
     * Get the value of supplier
     *
     * @return the value of supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Set the value of supplier
     *
     * @param supplier new value of supplier
     */
    public void setSupplier(Supplier supplier) {
        // Only set the supplier if it's different from the current one
        if (this.supplier != supplier) {
            this.supplier = supplier;
            if (supplier != null) {
                supplier.addOrder(this);
            }
        }
    }
    /**
     * Get the value of orderBeverageDetail
     *
     * @return the value of orderBeverageDetail
     */
    public List<OrderBeverageDetail> getOrderBeverageDetails() {
        return orderBeverageDetails;
    }

    /**
     * Set the value of orderBeverageDetail
     *
     * @param orderBeverageDetail new value of orderBeverageDetail
     */
    public void setOrderBeverageDetails(List<OrderBeverageDetail> orderBeverageDetails) {
        this.orderBeverageDetails = orderBeverageDetails;
    }
    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Order{" + "orderId=" + id + ", orderDate=" + orderDate + ", quantity=" + quantity + ", status= "+status+"}";
    }
    
    public String getOrderBeverageDetailstoString() {
        return "getOrderBeverageDetails: "+orderBeverageDetails;
}
}
