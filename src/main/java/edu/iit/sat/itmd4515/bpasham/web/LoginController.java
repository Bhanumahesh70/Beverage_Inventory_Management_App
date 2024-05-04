/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Login controller to check user authentication and have login and logout functionalities
 * @author bhanu
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @Inject
    SecurityContext securityContext;
    @Inject
    FacesContext facesContext;
    
    /**
     *
     */
    public LoginController() {
    }
    
    //model
        private User user;

    

    @PostConstruct
    private void postConstruct(){
        LOG.info("LoginController.postConstruct");
        
        //initialize the model
        user = new User();
    }
    
    //helper methods

    /**
     *Method to get check and get the AuthenticatedUser
     * @return
     */
    public String getAuthenticatedUser(){
        
        return securityContext.getCallerPrincipal().getName();
    }
    
    /**
     *Method to check if the logged in user is admin
     * @return
     */
    public boolean isAdmin(){
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }

    /**
     *Method to check if the logged in user is customer
     * @return
     */
    public boolean isCustomer(){
        return securityContext.isCallerInRole("CUSTOMER_ROLE");
    }

    /**
     *Method to check if the logged in user is manager
     * @return
     */
    public boolean isSalesManager(){
        return securityContext.isCallerInRole("SUPPLIER_ROLE");
    }

    /**
     *Method to check if the logged in user is InventoryManager
     * @return
     */
    public boolean isInventoryManager(){
        return securityContext.isCallerInRole("INVENTORY_MANAGER_ROLE");
    }
   
    //action methods

    /**
     *Method to login into the page
     * @return
     */
    public String doLogin(){
        LOG.info("LoginController.doLogin");
        HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        HttpServletResponse response= (HttpServletResponse)facesContext.getExternalContext().getResponse();
        Credential cred = new UsernamePasswordCredential(this.user.getUserName(), new Password(this.user.getPassword()));
        
       AuthenticationStatus status= securityContext.authenticate(request,response,AuthenticationParameters.withParams().credential(cred));
        
       switch(status){
           case SUCCESS:
               LOG.info(status.toString());
               break;
              
           case SEND_FAILURE:
               LOG.info("FAILURE!" + status.toString());
               return "/loginError.xhtml";
            
           case NOT_DONE:
               LOG.info("NOT DONE!" + status.toString());
               return "/loginError.xhtml";
               
           case SEND_CONTINUE:
               LOG.info(status.toString());
               break;
               
               
       } 
       return "/welcome.xhtml?faces-redirect=true";
    }
    
    /**
     *Method to logout of the page
     * @return
     */
    public String doLogout(){
        LOG.info("LoginController.doLogout");
        try {
            
            HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
            request.logout();
           
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
         return "/login.xhtml?faces-redirect=true";
    }
    
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }
}


