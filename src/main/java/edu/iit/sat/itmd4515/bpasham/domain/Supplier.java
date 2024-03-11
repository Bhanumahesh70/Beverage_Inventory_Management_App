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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
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
@NamedQuery(name ="Supplier.findAll ", query = "select s from Supplier s")
@Table(name = "SUPPLIER")
public class Supplier extends AbstractEntity{
   

    @NotBlank
    private String name;

    @NotBlank
    private String contactNumber;

    @NotNull
    private LocalDate createdAt;

    public Supplier() {
    }

    public Supplier(String name, String contactNumber, LocalDate createdAt) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne
    @JoinColumn(name="Beverage_ID")
    private Beverage beverage;
    
    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", createdAt=" + createdAt + '}';
    }

    
}
