/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import edu.iit.sat.itmd4515.bpasham.security.Group;
import edu.iit.sat.itmd4515.bpasham.security.GroupService;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@FacesConverter(value = "groupConverter", managed = true)
public class GroupConverter implements Converter<Group> {

    private static final Logger LOG = Logger.getLogger(GroupConverter.class.getName());

    @EJB
    GroupService groupService;

    /**
     *Method to covert group object to string 
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Group getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.info("Attempting to convert string to Group: " + value);
        Group group = groupService.findByName(value);
        LOG.info("Found Group: " + (group != null ? group.getGroupName() : "null"));
        return group;
    }

    /**
     *Method to covert from string to group object
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Group value) {
        String groupName = value != null ? value.getGroupName() : "";
        LOG.info("Converting Group to string: " + groupName);
        return groupName;
    }
}
