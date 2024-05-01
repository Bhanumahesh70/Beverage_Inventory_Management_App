/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import edu.iit.sat.itmd4515.bpasham.security.Group;
import edu.iit.sat.itmd4515.bpasham.security.User;
import edu.iit.sat.itmd4515.bpasham.security.GroupService;
import edu.iit.sat.itmd4515.bpasham.security.UserService;
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

    //Security realm srvices
    @EJB
    UserService userSvc;
    @EJB
    GroupService groupSvc;

    public StartupDbInitializer() {

    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("StartupDbInitializer.postConstruct");

        //Security Realm intialization first
        Group adminGroup = new Group("ADMIN_GROUP", "Group for administartive users");
        Group inventoryManagerGroup = new Group("INVENTORYMANAGER_GROUP", "Group for inventory managers");
        Group salesManagerGroup = new Group("SALESMANAGER_GROUP", "Group for sales managers");
        Group customerGroup = new Group("CUSTOMER_GROUP","Group for Customers");
        groupSvc.create(adminGroup);
        groupSvc.create(inventoryManagerGroup);
        groupSvc.create(salesManagerGroup);
        groupSvc.create(customerGroup);
        

        User admin1 = new User("admin1", "admin1");
        admin1.addGroup(adminGroup);
        admin1.addGroup(inventoryManagerGroup );
        admin1.addGroup(customerGroup);
         //admin1.addGroup(salesManagerGroup);
        userSvc.create(admin1);

        User admin2 = new User("admin2", "admin2");
        admin2.addGroup(adminGroup);
        admin2.addGroup(inventoryManagerGroup );
        userSvc.create(admin2);

        User invManager1 = new User("inventoryManager1", "inventoryManager1");
        invManager1.addGroup(inventoryManagerGroup);
        invManager1.addGroup(adminGroup);
        userSvc.create(invManager1);

        User invManager2 = new User("inventoryManager2", "inventoryManager2");
        invManager2.addGroup(inventoryManagerGroup);
        userSvc.create(invManager2);
        
        User salesManager1 = new User("salesManager1", "salesManager1");
        salesManager1.addGroup(salesManagerGroup);
        userSvc.create(salesManager1);

        User salesManager2 = new User("salesManager2", "salesManager2");
        salesManager2.addGroup(salesManagerGroup);
        userSvc.create(salesManager2);
        
         User customer1 = new User("customer1", "customer1");
        customer1.addGroup(customerGroup);
        userSvc.create(customer1);

        User customer2 = new User("customer2", "customer2");
        customer2.addGroup(customerGroup);
        userSvc.create(customer2);
        // End Security Realm intialization

        
        
        Beverage b1 = new Beverage("pure", LocalDate.of(2025, 2, 4), "false", BeverageType.WATER);
        Beverage b2 = new Beverage("life", LocalDate.of(2030, 2, 4), "false", BeverageType.WATER);
        Beverage b3 = new Beverage("Jose", LocalDate.of(2025, 2, 4), "false", BeverageType.WINE);
        Beverage b4 = new Beverage("Bacardi", LocalDate.of(2030, 2, 4), "false", BeverageType.LIQOUR);
        BeverageSvc.create(b1);
        BeverageSvc.create(b2);
         BeverageSvc.create(b3);
        BeverageSvc.create(b4);

        Supplier s1 = new Supplier("supplier one", "yoyo");
        s1.setUser(salesManager1);
        supplierServic.create(s1);
        s1.addBeverage(b1);
         s1.addBeverage(b3);
          s1.addBeverage(b4);
        
          Supplier s2 = new Supplier("supplier Two", "yesss");
        s2.setUser(salesManager2);
        supplierServic.create(s2);
       s2.addBeverage(b2);

        Customer c1 = new Customer("marry", "MaaryMe@gmail.com", LocalDate.of(2020, 2, 4));
        c1.setUser(customer1);
        CustomerSvc.create(c1);
        
        
        Customer c2 = new Customer("John", "Johnlly@gmail.com", LocalDate.of(2022, 6, 4));
        c2.setUser(customer2);
        CustomerSvc.create(c2);

        Inventory i1 = new Inventory(2, LocalDateTime.now());
        i1.setBeverage(b1);
        Inventory i2 = new Inventory(10, LocalDateTime.now());
        i2.setBeverage(b2);
        InventorySvc.create(i1);
        InventorySvc.create(i2);

        Order o1 = new Order(LocalDate.of(2024, 2, 4), 5);
        o1.addBeverage(b1);
        o1.setCustomer(c1);
        o1.setSupplier(s1);
        OrderSvc.create(o1);
        //CustomerSvc.create(c1);
        
        Order o2 = new Order(LocalDate.of(2024, 2, 5), 10);
        o2.addBeverage(b2);
        o2.setCustomer(c1);
        o2.setSupplier(s1);
        OrderSvc.create(o2);

        Order o3 = new Order(LocalDate.of(2024, 2, 5),3);
        o3.addOrderBeverageDetails(b4,2);
        o3.addOrderBeverageDetails(b1,5);
        o3.setCustomer(c1);
        o3.setSupplier(s1);
        OrderSvc.create(o3);

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
