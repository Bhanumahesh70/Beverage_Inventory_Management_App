/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    public LoginController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("LoginController.postConstruct");
    }
    
    //action method
    public String doLogin(){
        LOG.info("LoginController.doLogin");
        return "/welcome.xhtml";
    }
}
