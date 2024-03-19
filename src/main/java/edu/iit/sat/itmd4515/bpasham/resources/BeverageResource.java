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
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Path("/beverageinventory/beverages")
public class BeverageResource {

    private static final Logger LOG = Logger.getLogger(BeverageResource.class.getName());
    
    
     @EJB
    BeverageService BeverageSvc;
     
     @GET
     @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
     public List<Beverage> getAllBeverages(){
         return BeverageSvc.findAll();
     }
     
     @POST
     @Consumes(MediaType.APPLICATION_JSON)
     public void createANewBeverage(Beverage b){
         LOG.info("createANewBeverae with "+b.toString());
     }
}
