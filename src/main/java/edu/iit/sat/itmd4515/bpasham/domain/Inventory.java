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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "INVENTORY")
@NamedQuery(name="Inventory.findAll",query="select i from Inventory i")
public class Inventory extends AbstractEntity{
     
    @Min(value = 0, message = "Quantity must be positive")
    private Integer quantity;

    @NotNull
    private LocalDateTime lastUpdated;

    


    public Inventory() {
    }

    public Inventory(Integer quantity, LocalDateTime lastUpdated) {
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    
    @Override
    public String toString() {
        return "Inventory{" + "inventoryId=" + id + ", quantity=" + quantity + ", lastUpdated=" + lastUpdated + '}';
    }
}
