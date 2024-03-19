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
import java.util.List;

/**
 *
 * @author bhanu
 */
@Startup
@Singleton
public class StartupDbInitializer {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(StartupDbInitializer.class.getName());

    @EJB
    SupplierService supplierServic;
    @EJB
    BeverageService BeverageSvc;
    @EJB
    CustomerService CustomerSvc;

    //Owns Relations
    @EJB
    InventoryService InventorySvc;
    @EJB
    OrderService OrderSvc;

    public StartupDbInitializer() {

    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("StartupDbInitializer.postConstruct");

        Beverage b1 = new Beverage("pure", LocalDate.of(2025, 2, 4), "false", BeverageType.WATER);
        Beverage b2 = new Beverage("life", LocalDate.of(2030, 2, 4), "false", BeverageType.WATER);
        BeverageSvc.create(b1);
        BeverageSvc.create(b2);

        Supplier s1 = new Supplier("supplier one", "yoyo");
        supplierServic.create(s1);
        s1.setBeverage(b1);

        Customer c1 = new Customer("marry", "MaaryMe@gmail.com", LocalDate.of(2020, 2, 4));
        CustomerSvc.create(c1);

        Inventory i1 = new Inventory(2, LocalDateTime.now());
        i1.setBeverage(b1);
        Inventory i2 = new Inventory(10, LocalDateTime.now());
        i2.setBeverage(b2);
        InventorySvc.create(i1);
        InventorySvc.create(i2);

        Order o1 = new Order(LocalDate.of(2024, 2, 4), 5);
        o1.addBeverage(b1);
        o1.setCustomer(c1);

        Order o2 = new Order(LocalDate.of(2024, 2, 5), 10);
        o2.addBeverage(b2);
        o2.setCustomer(c1);

        OrderSvc.create(o1);
        OrderSvc.create(o2);

        /*
        for (Beverage b : BeverageSvc.findAll()) {
            LOG.info("=====================================================================\n");
            LOG.info(b.toString());

            LOG.info("\t============= Unidirectional 1:1 with Inventory =============== ");
            Inventory inventory = b.getInventory();
            if (inventory != null) {
                LOG.info("\t" + inventory.toString());
            } else {
                LOG.info("\tInventory is null for Beverage: " + b.getId());
            }

            LOG.info("\t============= Bidirectional 1:M with Supplier =============== ");
            List<Supplier> suppliers = b.getSuppliers();
            if (suppliers != null) {
                for (Supplier s : suppliers) {
                    LOG.info("\t" + s.toString());
                }
            } else {
                LOG.info("\tNo suppliers found for Beverage: " + b.getId());
            }
        }
        */
        
        //order relationships
        for (Order o : OrderSvc.findAll()) {
             LOG.info("=====================================================================\n");
            LOG.info(o.toString());
            
            LOG.info("\t============= Unidirectional M:1 with Customer =============== ");
            Customer customer = o.getCustomer();
            if (customer != null) {
                LOG.info("\t" + customer.toString());
            } else {
                LOG.info("\tCustomer is null for Beverage: " + o.getId());
            }
        }
        
        //Inventory relationships
        for (Inventory i : InventorySvc.findAll()) {
             LOG.info("=====================================================================\n");
            LOG.info(i.toString());
            
            LOG.info("\t============= Unidirectional 1:1 with Beverage =============== ");
            Beverage beverage = i.getBeverage();
            if (beverage != null) {
                LOG.info("\t" + beverage.toString());
            } else {
                LOG.info("\tBeverage is null for Inventory: " + i.getId());
            }
        }
        
        /*
        //Supplier relationships
        for (Supplier s : supplierServic.findAll()) {
             LOG.info("=====================================================================\n");
            LOG.info(s.toString());
            
            LOG.info("\t============= Unidirectional M:1 with Beverage =============== ");
            Beverage beverage = s.getBeverage();
            if (beverage != null) {
                LOG.info("\t" + beverage.toString());
            } else {
                LOG.info("\tBeverage is null for Supplier: " + s.getId());
            }
        }
*/
    }

}
