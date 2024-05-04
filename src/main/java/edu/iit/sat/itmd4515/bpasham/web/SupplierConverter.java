/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import edu.iit.sat.itmd4515.bpasham.service.SupplierService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author bhanu
 */
@FacesConverter(value="supplierConverter", managed=true)
public class SupplierConverter implements Converter<Supplier> {

    @EJB SupplierService supplierSvc;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Supplier getAsObject(FacesContext context, UIComponent component, String value) {
        //if we pass ID as string parameter, we need to get correct object for that ID
        return supplierSvc.read(Long.valueOf(value));
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Supplier value) {
        //if we pass object as parameter, we need to get cstring id for that object
        return String.valueOf(value.getId());
    }

    
    
}
