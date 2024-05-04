/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author bhanu
 */
@FacesConverter(value = "dateConverter", managed=true)
public class dateConverter implements Converter<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     *Method to covert date object to string 
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            throw new jakarta.faces.convert.ConverterException("The date must be in the format yyyy-MM-dd", e);
        }
    }

    /**
     *Method to covert from string to date object

     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
        if (value == null) {
            return null;
        }
        return value.format(formatter);
    }
}
