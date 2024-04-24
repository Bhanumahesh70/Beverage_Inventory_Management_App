/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "OrderBeverageDetail")
public class OrderBeverageDetail extends AbstractEntity {

    @Min(1)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "beverage_id")
    private Beverage beverage;

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
     * Get the value of order
     *
     * @return the value of order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Set the value of order
     *
     * @param order new value of order
     */
    public void setOrder(Order order) {
        this.order = order;
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

}
