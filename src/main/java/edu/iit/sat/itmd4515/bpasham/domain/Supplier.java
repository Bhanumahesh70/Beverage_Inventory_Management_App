/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import edu.iit.sat.itmd4515.bpasham.security.User;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@NamedQuery(name = "Supplier.findAll", query = "select s from Supplier s")
@NamedQuery(name = "Supplier.findByUsername", query = "select s from Supplier s where s.user.userName = :uname")
@Table(name = "SUPPLIER")
public class Supplier extends AbstractEntity {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    
    

    @ManyToMany
    @JsonbTransient
    //@XmlTransient
    @JoinTable(name = "supplier_beverage",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "beverage_id"))
    private List<Beverage> s_beverage;

    /**
     *No arg constructor
     */
    public Supplier() {
    }

    /**
     *constructor
     * @param name
     * @param email
     */
    public Supplier(String name, String email) {
        this.name = name;
        this.email = email;

    }

    //helper methods

    /**
     *Method for supplier to add beverages
     * @param b
     */
    public void addBeverage(Beverage b) {
        // Check if beverages list is null, initialize it if necessary
        if (this.s_beverage == null) {
            this.s_beverage = new ArrayList<>();
        }

        if (!this.s_beverage.contains(b)) {
            this.s_beverage.add(b);
        }

        // Check if b's orders list is null, initialize it if necessary
        if (b.getSuppliers() == null) {
            b.setSuppliers(new ArrayList<>());
        }

        if (!b.getSuppliers().contains(this)) {
            b.getSuppliers().add(this);
        }

    }

    /**
     *Method for supplier to remove beverages
     * @param b
     */
    public void removeBeverage(Beverage b) {
        if (this.s_beverage.contains(b)) {
            this.s_beverage.remove(b);
        }
        if (!b.getSuppliers().contains(this)) {
            b.getSuppliers().remove(this);
        }

    }

    /**
     *Method to add order to supplier
     * @param o
     */
    public void addOrder(Order o) {
        // Check if orderss list is null, initialize it if necessary
        if (this.orders == null) {
            this.orders = new ArrayList<>();
        }

        if (!this.orders.contains(o)) {
            this.orders.add(o);
            o.setSupplier(this);
        }

    }

    /**
     *Method to remove order to supplier
     * @param o
     */
    public void removeOrder(Order o) {
        if (this.orders.contains(o)) {
            this.orders.remove(o);
        }
        ////should remove supplier in order?
    }


    //Getter and Setter Methods

    /**
     *Get the value of name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *set the value of name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Get the value of email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *set the value of 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *Get the value of Beverage
     * @return
     */
    public List<Beverage> getBeverage() {
        return s_beverage;
    }

    /**
     *set the value of beverage
     * @param beverage
     */
    public void setBeverage(List<Beverage> beverage) {
        this.s_beverage = beverage;
    }

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the value of orders
     *
     * @return the value of orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Set the value of orders
     *
     * @param orders new value of orders
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     *Supplier To string method
     * @return
     */
    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + id + ", name=" + name + ", email=" + email + '}';
    }

}
