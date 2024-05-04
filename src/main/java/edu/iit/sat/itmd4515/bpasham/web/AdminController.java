/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import edu.iit.sat.itmd4515.bpasham.security.User;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import edu.iit.sat.itmd4515.bpasham.service.SupplierService;
import edu.iit.sat.itmd4515.bpasham.security.UserService;
import edu.iit.sat.itmd4515.bpasham.security.GroupService;
import java.util.List;
import java.util.logging.Logger;
/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class AdminController {
    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());
     @EJB
    private UserService userService;
      @EJB
    private GroupService groupService;
@EJB
    private SupplierService supplierService;

    /**
     *
     * @return
     */
    public List<User> getPendingSupplierRequests() {
        return userService.findUsersRequestingSupplierRole();
    }

    /**
     *
     * @param user
     * @return
     */
    public String approveSupplier(User user) {
        user.getGroups().add(groupService.findByName("SALESMANAGER_GROUP"));
        user.setRequestedSupplierRole(false);  // Reset the request flag
       User updatedUser= userService.updateUser(user);
        LOG.info("UserController.register creating supplier");
            Supplier supplier = new Supplier(updatedUser.getUserName(), updatedUser.getEmail());
            supplier.setUser(updatedUser);
            supplierService.create(supplier);
            LOG.info("UserController.register() created supplier " + supplier.toString());
        
        return "refresh";  // Refresh the page or navigate as needed
    }
}
