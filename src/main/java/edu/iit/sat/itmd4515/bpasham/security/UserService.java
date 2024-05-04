/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.security;

import edu.iit.sat.itmd4515.bpasham.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Stateless
public class UserService extends AbstractService<User> {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    /**
     *No arg constructor
     */
    public UserService() {
        super(User.class);
    }

    /**
     *Method to find all available Users
     * @return
     */
    public List<User> findAll() {
        return super.findAll("User.findAll");
    }

    /**
     * method to create a new user 
     * @param user
     */
    public void create(User user) {
        LOG.info("UserService.createUser");
        LOG.info("Creating a new user: " + user.getUserName());
        em.persist(user);
        LOG.info("User created successfully with ID: " + user.toString());
    }
}
