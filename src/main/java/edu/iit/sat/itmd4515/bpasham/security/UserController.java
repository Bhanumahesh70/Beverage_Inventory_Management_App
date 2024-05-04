/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.security;

import edu.iit.sat.itmd4515.bpasham.domain.Customer;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import edu.iit.sat.itmd4515.bpasham.service.SupplierService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
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
    private CustomerService customerService;

    @EJB
    private SupplierService supplierService;

    @EJB
    private GroupService groupService;

    private User newUser;
    private Group selectedRole;

    private List<Group> groups;

    /**
     * No arg constructor
     */
    public UserController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("UserController.postConstruct");
        newUser = new User();
        groups = groupService.findAll();

    }

    /**
     * =constructor
     *
     * @return
     */
    /*
    public String register() {
        LOG.info("inside UserController.register()");
        newUser.addGroup(selectedRole);
        userService.create(newUser);
        // Check role and create corresponding entity
        if ("CUSTOMER_GROUP".equals(selectedRole.getGroupName())) {
            LOG.info("UserController.register creating customer");
            Customer customer = new Customer(newUser.getUserName(), newUser.getEmail());
            customer.setUser(newUser);
            customerService.create(customer);
            LOG.info("UserController created customer " + customer.toString());
        } else if ("SALESMANAGER_GROUP".equals(selectedRole.getGroupName())) {
            LOG.info("UserController.register creating supplier");
            Supplier supplier = new Supplier(newUser.getUserName(), newUser.getEmail());
            supplier.setUser(newUser);
            supplierService.create(supplier);
            LOG.info("UserController.register() created supplier " + supplier.toString());
        } else {
            LOG.info("UserController.register() unable to createcustomer/supplier");
        }
        return "/login.xhtml?faces-redirect=true"; // Navigate to login after successful registration
    }
     */
    public String register() {
        LOG.info("inside UserController.register()");
        newUser.addGroup(groupService.findByName("CUSTOMER_GROUP"));
        LOG.info("UserController.register creating customer");
        userService.create(newUser);
        Customer customer = new Customer(newUser.getUserName(), newUser.getEmail());
        customer.setUser(newUser);
        customerService.create(customer);
        LOG.info("UserController created customer " + customer.toString());
        if (newUser.getRequestedSupplierRole()) {
            // Mark the user as requesting supplier access
            newUser.setRequestedSupplierRole(true);
            userService.update(newUser);  // Assume there's an update method in userService
        }
        return "/login.xhtml?faces-redirect=true";
    }

    /**
     * Method to return to home page from signup page when customer clicks back
     *
     * @return
     */
    public String cancel() {
        return "/login.xhtml?faces-redirect=true";
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
    /**
     * get the value of NewUser
     *
     * @return
     */
    public User getNewUser() {
        return newUser;
    }

    /**
     * set the value ofNewUser
     *
     * @param newUser
     */
    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    /**
     * get the value of SelectedRole
     *
     * @return
     */
    public Group getSelectedRole() {
        return selectedRole;
    }

    /**
     * set the value of SelectedRole
     *
     * @param selectedRole
     */
    public void setSelectedRole(Group selectedRole) {
        this.selectedRole = selectedRole;
    }
}
