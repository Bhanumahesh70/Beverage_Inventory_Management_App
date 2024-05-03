/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.security;

import edu.iit.sat.itmd4515.bpasham.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class UserController {
     private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    @EJB
    private UserService userService;

    @EJB
    private GroupService groupService;

    private User newUser ;
    private Group selectedRole;
    
    private List<Group> groups;

    public UserController() {
    }

    @PostConstruct
    private void postConstruct(){
       LOG.info("UserController.postConstruct");
       newUser= new User();
       groups = groupService.findAll();
        
    }


    public String register() {
        newUser.addGroup(selectedRole);
        userService.create(newUser);
        return "/login.xhtml?faces-redirect=true"; // Navigate to login after successful registration
    }

    
    /**
     * Get the value of groups
     *
     * @return the value of groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Set the value of groups
     *
     * @param groups new value of groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    // Getters and Setters
    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public Group getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Group selectedRole) {
        this.selectedRole = selectedRole;
    }
}
