/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    }

    
    @AfterEach
    public void afterEach() {
        
       // Beverage b = em.find(Beverage.class,1l);
       Beverage b = em.createQuery("select b from Beverage b where b.name = 'cocola'",Beverage.class).getSingleResult();
        
        
        tx.begin();
        em.remove(b);
        tx.commit();
        em.close();
    }

    
    @AfterAll
    public static void afterAll() {
        emf.close();
    }


}
