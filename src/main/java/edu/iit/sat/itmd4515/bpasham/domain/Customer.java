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
import jakarta.persistence.Table;
import jakarta.persistence.criteria.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    private LocalDate createdAt;

    /**@ManyToMany
    @JoinTable(name = "customer_order",
               joinColumns = @JoinColumn(name = "customer_id"),
               inverseJoinColumns = @JoinColumn(name = "order_id"))
    **/
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String name, String email, LocalDate createdAt, List<Order> orders) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.orders = orders;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.customerId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if(this.customerId==null || other.customerId==null){
            return false;
        }
        return Objects.equals(this.customerId, other.customerId);
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", name=" + name + ", email=" + email + ", createdAt=" + createdAt + ", orders=" + orders + '}';
    }

}
