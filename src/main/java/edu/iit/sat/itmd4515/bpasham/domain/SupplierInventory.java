/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "supplier_inventory")
public class SupplierInventory extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "beverage_id", nullable = false)
    private Beverage beverage;

    @Column(nullable = false)
    private Integer quantity;

    public SupplierInventory() {
    }

    
    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Set the value of quantity
     *
     * @param quantity new value of quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the value of beverage
     *
     * @return the value of beverage
     */
    public Beverage getBeverage() {
        return beverage;
    }

    /**
     * Set the value of beverage
     *
     * @param beverage new value of beverage
     */
    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * Get the value of supplier
     *
     * @return the value of supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Set the value of supplier
     *
     * @param supplier new value of supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
// String representation for debugging

    @Override
    public String toString() {
        return "SupplierInventory{"
                + "supplier=" + supplier.getName()
                + ", beverage=" + beverage.getName()
                + ", quantity=" + quantity
                + '}';
    }
}
