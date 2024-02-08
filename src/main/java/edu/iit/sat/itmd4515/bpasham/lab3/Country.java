/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.lab3;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author bhanu
 */
public class Country {
    
    @NotNull
    @NotBlank
    private String code;
    
     @NotBlank
     private String name;
     
     @NotNull
    // private String Continent;
     
     @NotNull
     @Positive
     private int IndepYear;
     
     @NotNull
     @Positive
     private int Population;
     
     @NotNull
     @Positive
     private int Capital;
    
     public Country(String Code,String Name, int IndepYear, int Population, int Capital){
         this.code = Code;
         this.name = Name;
         //this.Continent = Continent;
         this.IndepYear = IndepYear;
         this.Population = Population;
         this.Capital = Capital;
     }
     
     public Country(){
         
     }
     public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
    public String getContinent() {
        return Continent;
    }

    public void setContinent(String Continent) {
        this.Continent = Continent;
    }
    */

    public int getIndepYear() {
        return IndepYear;
    }

    public void setIndepYear(int IndepYear) {
        this.IndepYear = IndepYear;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int Population) {
        this.Population = Population;
    }

    public int getCapital() {
        return Capital;
    }

    public void setCapital(int Capital) {
        this.Capital = Capital;
    }

    
    /**@Override
    public String toString() {
        return "Country{" + "code=" + code + ", name=" + name + ", Continent=" + Continent + ", IndepYear=" + IndepYear + ", Population=" + Population + ", Capital=" + Capital + '}';
    }
    * */
    
    @Override
    public String toString() {
        return "Country{" + "code=" + code + ", name=" + name + ", IndepYear=" + IndepYear + ", Population=" + Population + ", Capital=" + Capital + '}';
    }
    
    
}
