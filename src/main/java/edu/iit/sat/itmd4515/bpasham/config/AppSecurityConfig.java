/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 *
 * @author bhanu
 */
@Named
@ApplicationScoped
@DeclareRoles({"ADMIN_ROLE","OWNER_ROLE","VET_ROLE"})
public class AppSecurityConfig {
    
}
