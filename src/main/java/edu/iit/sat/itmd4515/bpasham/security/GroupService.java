/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.security;

import edu.iit.sat.itmd4515.bpasham.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;
/**
 *
 * @author bhanu
 */
@Stateless
public class GroupService extends AbstractService<Group> {
 
     private static final Logger LOG = Logger.getLogger(GroupService.class.getName());
    public GroupService() {
        super(Group.class);
    }
    
     public List<Group> findAll(){
         LOG.info("Inside GroupService.findAll()");
         List<Group> groups = super.findAll("Group.findAll");
    LOG.info("Fetched " + groups.size() + " groups.");
    return groups;
    }
    public Group findByName(String groupName) {
        LOG.info("Inside GroupService.findByName");
    return em.createNamedQuery("Group.findByName", Group.class)
                        .setParameter("groupName", groupName)
                        .getSingleResult();
}

}
