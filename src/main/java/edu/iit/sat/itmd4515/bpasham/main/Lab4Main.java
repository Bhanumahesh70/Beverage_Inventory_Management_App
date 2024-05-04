/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.main;

import edu.iit.sat.itmd4515.bpasham.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

/**
 *
 * @author bhanu
 */
public class Lab4Main {

    /**
     *
     * @param args
     */
    public static void main(String... args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Beverage b1 = new Beverage("cocola", LocalDate.of(2025, 3, 5), "true",BeverageType.SODA);

        tx.begin();
        em.persist(b1);
        tx.commit();

        em.close();
        emf.close();
    }

}
