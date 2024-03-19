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
import edu.iit.sat.itmd4515.bpasham.domain.Inventory;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @EJB CustomerService CustomerSvc;
    
    //Owns Relations
    @EJB InventoryService InventorySvc;
    @EJB OrderService OrderSvc;
    public StartupDbInitializer(){
        
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("StartupDbInitializer.postConstruct");
        
        Supplier s1 = new Supplier("supplier one","yoyo");
        supplierService.create(s1);
         Customer c1 = new Customer("marry","MaaryMe@gmail.com",LocalDate.of(2020,2,4));
        CustomerSvc.create(c1);
        
        //not working
        Beverage b1 = new Beverage("pure", LocalDate.of(2025,2,4),"false",BeverageType.WATER);
        Beverage b2 = new Beverage("life", LocalDate.of(2030,2,4),"false",BeverageType.WATER);
        BeverageSvc.create(b1);
        BeverageSvc.create(b2);
        
        Inventory i1 = new Inventory(2,LocalDateTime.now());
        i1.setBeverage(b1);
        Inventory i2 = new Inventory(10,LocalDateTime.now());
        i2.setBeverage(b2);
       InventorySvc.create(i1);
       InventorySvc.create(i2);
        
       Order o1 = new Order(LocalDate.of(2024,2,4),5);
       o1.addBeverage(b1);
       
       Order o2 = new Order(LocalDate.of(2024,2,5),10);
       o2.addBeverage(b2);
      
       OrderSvc.create(o1);
       OrderSvc.create(o2);
       
       
        
        
    }
   
}
