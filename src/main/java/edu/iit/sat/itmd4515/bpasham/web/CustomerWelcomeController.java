/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import edu.iit.sat.itmd4515.bpasham.domain.Customer;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class CustomerWelcomeController {

    private static final Logger LOG = Logger.getLogger(CustomerWelcomeController.class.getName());

    @EJB
    CustomerService customerSvc;
    @Inject
    LoginController loginController;

    //the model
    private Customer customer;

    /**
     *
     */
    public CustomerWelcomeController() {
    }

    @PostConstruct
    private void postConstruct() {

        LOG.info("CustomerWelcomeController.postConstruct");
        //initialize our model, not with a new customer, but with finding the customer correlated with the currently authenticated user

        // Get the authenticated user's username
        String authenticatedUser = loginController.getAuthenticatedUser();

        // Check if the authenticated user is an admin
        if (loginController.isAdmin()) {
            // Initialize admin model
           // admin = adminSvc.findByUsername(authenticatedUser);
            LOG.info("AdminWelcomeController.postConstruct: ");
        } else {
            // Initialize customer model
            customer = customerSvc.findByUsername(authenticatedUser);
            LOG.info("CustomerWelcomeController.postConstruct: " + customer.toString());
        }
    }

    //utility or helper methods

    /**
     *
     */
    public void refreshCustomerModel() {
        LOG.info("CustomerWelcomeController.refreshCustomerModel()");
        customer = customerSvc.findByUsername(loginController.getAuthenticatedUser());
    }

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
