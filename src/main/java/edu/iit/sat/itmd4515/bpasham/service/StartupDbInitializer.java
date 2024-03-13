/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.lang.System.Logger;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.domain.BeverageType;
import edu.iit.sat.itmd4515.bpasham.domain.Customer;
import java.time.LocalDate;

/**
 *
 * @author bhanu
 */

@Startup
@Singleton
public class StartupDbInitializer {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(StartupDbInitializer.class.getName());
    
    @EJB SupplierService supplierService;
    @EJB BeverageService BeverageSvc;
    @EJB CustomerService Customersvc;
    public StartupDbInitializer(){
        
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("StartupDbInitializer.postConstruct");
        
        Supplier s1 = new Supplier("supplier one","yoyo");
        supplierService.create(s1);
        
        Beverage b1 = new Beverage("pure", LocalDate.of(2025,2,4),"no",BeverageType.WATER);
        Beverage b2 = new Beverage("life", LocalDate.of(2030,2,4),"no",BeverageType.WATER);
        //BeverageSvc.create(b1);
        //BeverageSvc.create(b2);
        
        Customer c1 = new Customer("marry","MaaryMe@gmail.com",LocalDate.of(2020,2,4));
        Customersvc.create(c1);
    }
   
}
