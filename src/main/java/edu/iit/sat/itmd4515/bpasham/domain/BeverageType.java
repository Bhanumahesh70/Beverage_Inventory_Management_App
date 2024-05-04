/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

/**
 *
 * @author bhanu
 */
public enum BeverageType {

    /**
     *
     */
    SODA("Cool Soda"),

    /**
     *
     */
    WATER("Chill Water"),

    /**
     *
     */
    WINE("Old Wine"),

    /**
     *
     */
    LIQOUR("Hard Liqour"),

    /**
     *
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
