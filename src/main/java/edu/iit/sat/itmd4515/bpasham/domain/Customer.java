/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import edu.iit.sat.itmd4515.bpasham.security.User;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *Customer Entity
 * @author bhanu
 */
@Entity
@Table(name = "CUSTOMER")
@NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
@NamedQuery(name = "Customer.findByUsername", query = "select c from Customer c where c.user.userName = :uname")
public class Customer extends AbstractEntity {

    @NotBlank
    private String name;

    @Email(message = "Enter Valid Email @")
    private String email;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    private static final Logger LOG = Logger.getLogger(Customer.class.getName());

    /**
     *NoArg Constructor
     */
    public Customer() {
    }

    /**
     *Constructor
     * @param name
     * @param email
     */
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     *Method to add order to customer
     * @param o
     */
    public void addOrder(Order o) {
        LOG.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%orders before added: " + orders);
        // Check if orders list is null, initialize it if necessary
        if (this.orders == null) {
            this.orders = new ArrayList<>();
        }

        if (!this.orders.contains(o)) {
            this.orders.add(o);
            o.setCustomer(this); // Ensure bidirectional consistency
        }
        LOG.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%orders before added: " + orders);
    }

    //Getter and Setter Methods

    /**
     *get name of customer
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *set name of customer
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *get email of customer
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *set email of customer
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
     *To string method
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" + "customerId=" + id + ", name=" + name + ", email=" + email + ", " + "orders" + orders + '}';
    }

}
