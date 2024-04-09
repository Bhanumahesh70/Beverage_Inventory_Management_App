/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import edu.iit.sat.itmd4515.bpasham.security.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@NamedQuery(name = "Supplier.findAll ", query = "select s from Supplier s")
@NamedQuery(name = "Supplier.findByUsername", query = "select s from Supplier s where s.user.userName = :uname")
@Table(name = "SUPPLIER")
public class Supplier extends AbstractEntity {

    @NotBlank
    private String name;

    @NotBlank
    private String contactNumber;

    @OneToOne
    @JoinColumn(name="USERNAME")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Beverage_ID")
    private Beverage s_beverage;

    public Supplier() {
    }

    public Supplier(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Beverage getBeverage() {
        return s_beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.s_beverage = beverage;
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
        return "Supplier{" + "supplierId=" + id + ", name=" + name + ", contactNumber=" + contactNumber + '}';
    }

}
