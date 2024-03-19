/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.resources;

import edu.iit.sat.itmd4515.bpasham.service.BeverageService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Path;
import java.util.List;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import jakarta.ws.rs.GET;

/**
 *
 * @author bhanu
 */
@Path("/beverageinventory/beverages")
public class BeverageResource {
    
     @EJB
    BeverageService BeverageSvc;
     
     @GET
     public List<Beverage> getAllBeverages(){
         return BeverageSvc.findAll();
     }
}
