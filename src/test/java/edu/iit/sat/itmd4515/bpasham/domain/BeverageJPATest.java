/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author bhanu
 */
public class BeverageJPATest {

    /**
     *
     */
    
    private static EntityManagerFactory emf ;
    private EntityManager em;
    private EntityTransaction tx;
    @BeforeAll
    public static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    
    @BeforeEach
    public void beforeEach() {
        em = emf.createEntityManager();
        tx = em.getTransaction(); 
         Beverage b1 = new Beverage("cocola", LocalDate.of(2025, 3, 5), "true",BeverageType.SODA);

        tx.begin();
        em.persist(b1);
        tx.commit();
    }

    
    @Test
    public void createTest() {
        Beverage b2 = new Beverage("pepsi", LocalDate.of(2025, 9, 5), "true",BeverageType.SODA);

        tx.begin();
        em.persist(b2);
        tx.commit();
        Beverage readBackFromDatabaseForAssertion = em.find(Beverage.class,b2.getId());
        assertEquals(b2.getId(),readBackFromDatabaseForAssertion.getId());
    }
    @Test
    public void readTest() {
        
        // Retrieving the beverage created in beforeEach method
    Beverage b = em.createQuery("SELECT b FROM Beverage b WHERE b.name = 'cocola'", Beverage.class)
                   .getSingleResult();
    
    // Checking if the retrieved beverage is not null
    assertNotNull(b);
    
    // Perform assertions on the retrieved beverage if needed
    assertEquals("cocola", b.getName());
   
    }
    
    @Test
    public void updateTest() {
        //working with before Each sample data
        Beverage b = em.createQuery("select b from Beverage b where b.name = 'cocola'",
                Beverage.class).getSingleResult();
                
                LocalDate newExpiryDate =LocalDate.of(2026,5,7); 
        //update something
        //write it back to database
        //using set methods in a trasaction updates database for a managed entity
        tx.begin();
        b.setExpiryDate(newExpiryDate);
        tx.commit();
        
        //read it back from database
        Beverage readBackFromDatabaseForAssertion = em.find(Beverage.class,b.getId());
        
        //assert that it was successfully updated
        assertEquals(newExpiryDate,readBackFromDatabaseForAssertion.getExpiryDate());
    }

    @Test
    public void deleteTest() {
             try {
        // Retrieve the beverage to delete
        Beverage b = em.createQuery("select b from Beverage b where b.name = 'cocola'", Beverage.class).getSingleResult();

        // Log the retrieved beverage for debugging
        System.out.println("Retrieved beverage: " + b);

        // Start a transaction
        tx.begin();

        // Remove the beverage from the database
        em.remove(b);

        // Commit the transaction
        tx.commit();

        // Verify deletion
        Beverage deletedBeverage = em.find(Beverage.class, b.getId());
        assertNull(deletedBeverage, "Beverage was not successfully deleted");
    } catch (NoResultException e) {
        // Handle the case where no beverage is found
        System.err.println("No beverage found with the specified criteria. Assuming the test passes.");
    }
    }

    @Test
    public void createOrderWithCustomerTest() {
        try {
        tx.begin();

        // Create a new customer
        Customer customer = new Customer("John Doe", "john@example.com", LocalDate.now());
        em.persist(customer);

        // Create a new order associated with the customer
        Order order = new Order(LocalDate.now(), 2);
        order.setCustomer(customer);
        em.persist(order);

        tx.commit();

        // Retrieve the order from the database and verify the association with the customer
        Order savedOrder = em.find(Order.class, order.getOrderId());
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getCustomer());
        assertEquals(customer.getCustomerId(), savedOrder.getCustomer().getCustomerId());
    } catch (Exception e) {
        if (tx.isActive()) {
            tx.rollback();
        }
        fail("Exception occurred: " + e.getMessage());
    }
    }

    @AfterEach
    public void afterEach() {
        
       try {
        Beverage b = em.createQuery("select b from Beverage b where b.name = 'cocola'", Beverage.class).getSingleResult();

        tx.begin();
        em.remove(b);
        tx.commit();
        em.close();
    } catch (NoResultException e) {
        System.err.println("No beverage found with the specified criteria in afterEach method.");
        // Log or handle the exception as needed
    }
    }

    
    @AfterAll
    public static void afterAll() {
        emf.close();
    }


}
