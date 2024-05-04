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
        

        User admin1 = new User("admin1", "admin1","admin1@gmail.com");
        admin1.addGroup(adminGroup);
        admin1.addGroup(inventoryManagerGroup );
        admin1.addGroup(customerGroup);
         //admin1.addGroup(salesManagerGroup);
        userSvc.create(admin1);

        User admin2 = new User("admin2", "admin2","admin2@gmail.com");
        admin2.addGroup(adminGroup);
        admin2.addGroup(inventoryManagerGroup );
        userSvc.create(admin2);

        User invManager1 = new User("inventoryManager1", "inventoryManager1","im1@gmail.com");
        invManager1.addGroup(inventoryManagerGroup);
        invManager1.addGroup(adminGroup);
        userSvc.create(invManager1);

        User invManager2 = new User("inventoryManager2", "inventoryManager2","im2@gmail.com");
        invManager2.addGroup(inventoryManagerGroup);
        userSvc.create(invManager2);
        
        User salesManager1 = new User("salesManager1", "salesManager1","salesManager1@gmail.com");
        salesManager1.addGroup(salesManagerGroup);
        userSvc.create(salesManager1);

        User salesManager2 = new User("salesManager2", "salesManager2","salesManager2@gmail.com");
        salesManager2.addGroup(salesManagerGroup);
        userSvc.create(salesManager2);
        
         User customer1 = new User("customer1", "customer1","customer1@gmail.com");
        customer1.addGroup(customerGroup);
        userSvc.create(customer1);

        User customer2 = new User("customer2", "customer2","customer2@gmail.com");
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

        Supplier s1 = new Supplier("supplier one", "Supplier1@gmail.com");
        s1.setUser(salesManager1);
        supplierServic.create(s1);
        s1.addBeverage(b1);
         s1.addBeverage(b3);
          s1.addBeverage(b4);
        
          Supplier s2 = new Supplier("supplier Two", "Supplier2@gmail.com");
        s2.setUser(salesManager2);
        supplierServic.create(s2);
       s2.addBeverage(b2);

        Customer c1 = new Customer("marry", "MaaryMe@gmail.com");
        c1.setUser(customer1);
        CustomerSvc.create(c1);
        
        
        Customer c2 = new Customer("John", "Johnlly@gmail.com");
        c2.setUser(customer2);
        CustomerSvc.create(c2);

        

        Order o1 = new Order(LocalDate.of(2024, 2, 4), 5);
        o1.addBeverage(b1);
        o1.addOrderBeverageDetails(b1,5);
        o1.setCustomer(c1);
        o1.setSupplier(s1);
        OrderSvc.create(o1);
        //CustomerSvc.create(c1);
        
        Order o2 = new Order(LocalDate.of(2024, 2, 5), 6);
        o2.addOrderBeverageDetails(b2,6);
        o2.addBeverage(b2);
        o2.setCustomer(c2);
        o2.setSupplier(s1);
        OrderSvc.create(o2);

        Order o3 = new Order(LocalDate.of(2024, 2, 7),7);
        o3.addOrderBeverageDetails(b4,2);
        o3.addOrderBeverageDetails(b1,5);
        o3.addBeverage(b4);
        o3.addBeverage(b1);
        
        o3.setCustomer(c1);
        o3.setSupplier(s1);
        OrderSvc.create(o3);
        
        Order o4 = new Order(LocalDate.of(2024, 6, 10),10);
        o4.addOrderBeverageDetails(b1,1);
        o4.addOrderBeverageDetails(b2,2);
        o4.addOrderBeverageDetails(b3,3);
        o4.addOrderBeverageDetails(b4,4);
        o4.addBeverage(b1);
        o4.addBeverage(b2);
        o4.addBeverage(b3);
        o4.addBeverage(b4);
        
        o4.setCustomer(c2);
        o4.setSupplier(s2);
        OrderSvc.create(o4);
        
        Order o5 = new Order(LocalDate.of(2024, 6, 10),12);
        o5.addOrderBeverageDetails(b1,4);
        o5.addOrderBeverageDetails(b2,3);
        o5.addOrderBeverageDetails(b3,2);
        o5.addOrderBeverageDetails(b4,3);
        o5.addBeverage(b1);
        o5.addBeverage(b2);
        o5.addBeverage(b3);
        o5.addBeverage(b4);
        o5.setStatus("Deleted");
        o5.setCustomer(c1);
        o5.setSupplier(s1);
        OrderSvc.create(o5);
        
        Order o6 = new Order(LocalDate.of(2024, 6, 10),11);
        o6.addOrderBeverageDetails(b3,5);
        o6.addOrderBeverageDetails(b4,6);
        o6.addBeverage(b1);
        o6.addBeverage(b2);
        o6.addBeverage(b3);
        o6.addBeverage(b4);
        o6.setStatus("Completed");
        o6.setCustomer(c1);
        o6.setSupplier(s1);
        OrderSvc.create(o6);

       
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

       
    }

}
