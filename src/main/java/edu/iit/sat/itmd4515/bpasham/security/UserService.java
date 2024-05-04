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
     * No arg constructor
     */
    public UserService() {
        super(User.class);
    }

    /**
     * Method to find all available Users
     *
     * @return
     */
    public List<User> findAll() {
        return super.findAll("User.findAll");
    }

    /**
     *
     * @return
     */
    public List<User> findUsersRequestingSupplierRole() {
        return em.createQuery("SELECT u FROM User u WHERE u.requestedSupplierRole = true", User.class).getResultList();
    }

    /**
     * method to create a new user
     *
     * @param user
     */
    public void create(User user) {
        LOG.info("UserService.createUser");
        LOG.info("Creating a new user: " + user.getUserName());
        em.persist(user);
        LOG.info("User created successfully with ID: " + user.toString());
    }

    /**
     *
     * @param user
     * @param newPassword
     */
    public void updateUser(User user, String newPassword) {
    if (newPassword != null && !newPassword.isEmpty()) {
        user.setPassword(newPassword);  // This sets the passwordChanged flag internally
    }
    em.merge(user);
}
    
    /**
     *
     * @param user
     * @return
     */
    public User updateUser(User user) {
        LOG.info("Updating user: " + user.getUserName());
        user.setPasswordChanged(false);
        User updatedUser = em.merge(user); // Merge the state of the given entity into the current persistence context.
        LOG.info("User updated successfully with ID: " + updatedUser.getUserName());
        return updatedUser;
    }
}
