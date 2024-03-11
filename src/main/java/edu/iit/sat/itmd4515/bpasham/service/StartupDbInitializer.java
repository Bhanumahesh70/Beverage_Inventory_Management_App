/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.lang.System.Logger;

/**
 *
 * @author bhanu
 */

@Startup
@Singleton
public class StartupDbInitializer {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(StartupDbInitializer.class.getName());
    
    public StartupDbInitializer(){
        
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("StartupDbInitializer.postConstruct");
    }
   
}
