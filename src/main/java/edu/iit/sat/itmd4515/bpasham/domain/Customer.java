/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import edu.iit.sat.itmd4515.bpasham.security.User;
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
import jakarta.persistence.criteria.Order;
import jakarta.validation.constraints.Email;
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
@Table(name = "CUSTOMER")
@NamedQuery(name="Customer.findAll",query="select c from Customer c")
@NamedQuery(name = "Customer.findByUsername", query = "select c from Customer c where c.user.userName = :uname")
public class Customer extends AbstractEntity{
      

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    private LocalDate createdAt;
    
    @OneToOne
    @JoinColumn(name="USERNAME")
    private User user;

    


    
//   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Order> orders = new ArrayList<>();
//   
    public Customer() {
    }

    public Customer(String name, String email, LocalDate createdAt) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
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
   
    @Override
    public String toString() {
        return "Customer{" + "customerId=" + id + ", name=" + name + ", email=" + email + ", createdAt=" + createdAt + '}';
    }


}
