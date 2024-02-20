/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "BEVERAGE")
public class Beverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BEVERAGE_ID")
    private Long id;

    @NotBlank
    @Column(name = "BEVERAGE_NAME", nullable=false,unique=true)
    private String name;

    @NotNull
    @Column(name = "BEVERAGE_EXPIRYDATE")
    private LocalDate expiryDate;

    @NotNull
    @Pattern(regexp = "^(true|false)$", message = "The boolean variable must be either true or false")
    @Column(name = "IS_NON_ALCOHOLIC")
    private String isNonAlcoholic;
    
    @NotNull
    @Enumerated(EnumType.STRING)
        private BeverageType type;

    
    
    public Beverage(String name, LocalDate expiryDate, String isNonAlcoholic, BeverageType type) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.isNonAlcoholic = isNonAlcoholic;
        this.type = type;
    }
    
    @OneToMany(mappedBy = "beverage")
    private List<Supplier> suppliers = new ArrayList<>();

    /**
     * Get the value of suppliers
     *
     * @return the value of suppliers
     */
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * Set the value of suppliers
     *
     * @param suppliers new value of suppliers
     */
    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }


    

    public Beverage() {
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Beverage other = (Beverage) obj;
        if(this.id==null || other.id==null){
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    
    
    // Getter and setter Methods

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public BeverageType getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(BeverageType type) {
        this.type = type;
    }
    /**
     * Get the value of isNonAlcoholic
     *
     * @return the value of isNonAlcoholic
     */
    public String getIsNonAlcoholic() {
        return isNonAlcoholic;
    }

    /**
     * Set the value of isNonAlcoholic
     *
     * @param isNonAlcoholic new value of isNonAlcoholic
     */
    public void setIsNonAlcoholic(String isNonAlcoholic) {
        this.isNonAlcoholic = isNonAlcoholic;
    }

    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of expiryDate
     *
     * @return the value of expiryDate
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Set the value of expiryDate
     *
     * @param expiryDate new value of expiryDate
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Beverage{" + "id=" + id + ", name=" + name + ", expiryDate=" + expiryDate + ", isNonAlcoholic=" + isNonAlcoholic + ", type=" + type + '}';
    }

}
