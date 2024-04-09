/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import edu.iit.sat.itmd4515.bpasham.security.User;
import jakarta.json.bind.annotation.JsonbTransient;
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

    @ManyToMany
    @JsonbTransient
     //@XmlTransient
    @JoinTable(name = "supplier_beverage",
               joinColumns = @JoinColumn(name = "supplier_id"),
               inverseJoinColumns = @JoinColumn(name = "beverage_id"))
    private List<Beverage> s_beverage;

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

    public void addBeverage(Beverage b){
        // Check if beverages list is null, initialize it if necessary
    if (this.s_beverage == null) {
        this.s_beverage = new ArrayList<>();
    }

    if(!this.s_beverage.contains(b)){
        this.s_beverage.add(b);
    }

    // Check if b's orders list is null, initialize it if necessary
    if (b.getSuppliers()== null) {
        b.setSuppliers(new ArrayList<>());
    }

    if(!b.getSuppliers().contains(this)){
        b.getSuppliers().add(this);
    }
        
    }
    public void removeBeverage(Beverage b){
        if(!this.s_beverage.contains(b)){
            this.s_beverage.remove(b);
        }
         if(!b.getSuppliers().contains(this)){
            b.getSuppliers().remove(this);
        }
        
    }
    public List<Beverage> getBeverage() {
        return s_beverage;
    }

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

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + id + ", name=" + name + ", contactNumber=" + contactNumber + '}';
    }

}
