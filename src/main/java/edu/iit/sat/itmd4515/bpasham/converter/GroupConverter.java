/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.converter;

import edu.iit.sat.itmd4515.bpasham.security.Group;
import edu.iit.sat.itmd4515.bpasham.security.GroupService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

/**
 *
 * @author bhanu
 */
/*
@FacesConverter(value = "groupConverter", managed = true)
public class GroupConverter implements Converter<Group>{
    @Inject
    private GroupService groupService;

    @Override
    public Group getAsObject(FacesContext context, UIComponent component, String value) {
        return groupService.findByName(value); // Implement this method in GroupService
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Group value) {
        return value.getGroupName();
    }
}
*/