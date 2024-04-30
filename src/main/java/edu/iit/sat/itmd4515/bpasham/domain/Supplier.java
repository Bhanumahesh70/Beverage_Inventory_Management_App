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
    private String contactNumber;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierInventory> inventoryList = new ArrayList<>();
    ;

    

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

    //helper methods
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

    public void removeBeverage(Beverage b) {
        if (this.s_beverage.contains(b)) {
            this.s_beverage.remove(b);
        }
        if (!b.getSuppliers().contains(this)) {
            b.getSuppliers().remove(this);
        }

    }

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

    public void removeOrder(Order o) {
        if (this.orders.contains(o)) {
            this.orders.remove(o);
        }
        ////should remove supplier in order?
    }
// Helper methods for managing inventory

    public void addInventoryItem(Beverage beverage, int quantity) {
        SupplierInventory inventory = new SupplierInventory();
        inventory.setBeverage(beverage);
        inventory.setSupplier(this);
        inventory.setQuantity(quantity);
        inventoryList.add(inventory);
        beverage.getInventoryList().add(inventory);
    }

    public void removeInventoryItem(Beverage beverage) {
        SupplierInventory item = inventoryList.stream()
                .filter(inventory -> inventory.getBeverage().equals(beverage))
                .findFirst()
                .orElse(null);
        if (item != null) {
            inventoryList.remove(item);
            beverage.getInventoryList().remove(item);
        }
    }

    //Getter and Setter Methods
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
     * Get the value of inventoryList
     *
     * @return the value of inventoryList
     */
    public List<SupplierInventory> getInventoryList() {
        return inventoryList;
    }

    /**
     * Set the value of inventoryList
     *
     * @param inventoryList new value of inventoryList
     */
    public void setInventoryList(List<SupplierInventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + id + ", name=" + name + ", contactNumber=" + contactNumber + '}';
    }

}
