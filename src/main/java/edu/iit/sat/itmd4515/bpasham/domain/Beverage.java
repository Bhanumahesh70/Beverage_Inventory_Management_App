/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@XmlRootElement
@Table(name = "BEVERAGE")
@NamedQuery(name="Beverage.findAll",query="select b from Beverage b")
public class Beverage extends AbstractEntity{


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
   
    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    

   

    /**
     * Get the value of isDeleted
     *
     * @return the value of isDeleted
     */
    


    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
    @NotNull
    @Enumerated(EnumType.STRING)
        private BeverageType type;

    @OneToMany(mappedBy = "beverage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderBeverageDetail> orderBeverageDetails;
    
    @ManyToMany(mappedBy = "beverages")
    @XmlTransient
    private List<Order> orders;
    
   
    
    @ManyToMany(mappedBy = "s_beverage")
    private List<Supplier> suppliers = new ArrayList<>();
    
    @OneToOne(mappedBy = "beverage")
    private Inventory inventory;
    
    

    
    public Beverage() {
    }
      public Beverage(String name, LocalDate expiryDate, String isNonAlcoholic, BeverageType type) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.isNonAlcoholic = isNonAlcoholic;
        this.type = type;
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
    
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
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
    /**
     * Get the value of orderBeverageDetails
     *
     * @return the value of orderBeverageDetails
     */
    public List<OrderBeverageDetail> getOrderBeverageDetails() {
        return orderBeverageDetails;
    }

    /**
     * Set the value of orderBeverageDetails
     *
     * @param orderBeverageDetails new value of orderBeverageDetails
     */
    public void setOrderBeverageDetails(List<OrderBeverageDetail> orderBeverageDetails) {
        this.orderBeverageDetails = orderBeverageDetails;
    }
   
    /**
     * Get the value of isDeleted
     *
     * @return the value of isDeleted
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * Set the value of isDeleted
     *
     * @param isDeleted new value of isDeleted
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


    //To String Method
    @Override
    public String toString() {
        return "Beverage{" + "id=" + id + ", name=" + name + ", expiryDate=" + expiryDate + ", isNonAlcoholic=" + isNonAlcoholic + ", type=" + type + '}';
    }

}
