/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author bhanu
 */
@Named
@Stateless
public class BeverageService extends AbstractService<Beverage>{

    public BeverageService() {
        super(Beverage.class);
    }
    
    
    public List<Beverage> findAll(){
        return super.findAll("Beverage.findAll");
    }
    
}
