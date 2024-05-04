/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.security;

import jakarta.inject.Inject;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author bhanu
 */
public class UserPasswordHash {
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(UserPasswordHash.class.getName());
    @Inject private Pbkdf2PasswordHash hash;
            
    @PrePersist
    @PreUpdate
    private void hashPassword(User u){
        
        // Only hash the password if it's a new entity or if the password has been modified.
        LOG.info("Password hashing");
        //LOG.info("user: "+u.getUserName());
        if (u.isPasswordChanged()) {
            u.setPassword(hash.generate(u.getPassword().toCharArray()));
            u.setPasswordChanged(false);
        }else{
            LOG.info("Inside UserPasswordHash else{}");
            LOG.info("user: "+u.toString());
        }
    }
}
