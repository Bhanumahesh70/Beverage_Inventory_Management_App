/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.domain.Customer;
import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author bhanu
 */
@FacesConverter(value="customerConverter", managed=true)
public class CustomerConverter implements Converter<Customer> {
    @EJB CustomerService customerSvc;
    @Override
    public Customer  getAsObject(FacesContext context, UIComponent component, String value) {
        //if we pass ID as string parameter, we need to get correct object for that ID
        return customerSvc.read(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Customer value) {
        //if we pass object as parameter, we need to get cstring id for that object
        return String.valueOf(value.getId());
    }

    
}
