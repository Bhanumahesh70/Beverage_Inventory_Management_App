/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

/**
 *Types of Beverage Entity
 * @author bhanu
 */
public enum BeverageType {

    /**
     * SODA type
     */
    SODA("Cool Soda"),

    /**
     *Water Beverage
     */
    WATER("Chill Water"),

    /**
     * Wine types beverage
     */
    WINE("Old Wine"),

    /**
     *Liquor type
     */
    LIQOUR("Hard Liqour"),

    /**
     *Juice type
     */
    JUICE("Tasty Juice");
    
    private String label;

    private BeverageType(String label) {
        this.label=label;
    }
    
    /**
     *
     * @return
     */
    public String getLabel(){
        return label;
    }
}
