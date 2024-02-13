/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    
    @BeforeAll
    public static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    ;
    @BeforeEach
    public void beforeEach() {
        em = emf.createEntityManager();
    }

    ;
    
    @Test
    public void createTest() {
    }
    @Test
    public void readTest() {
    }
    @Test
    public void updateTest() {
    }
    @Test
    public void deleteTest() {
    }

    ;
    @AfterEach
    public void afterEach() {
    }

    ;
    @AfterAll
    public static void afterAll() {
    }
;

}
