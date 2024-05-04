/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.security;

import edu.iit.sat.itmd4515.bpasham.security.Group;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhanu
 */
@Entity
@Table(name = "SEC_USER")
@EntityListeners(UserPasswordHash.class)
@NamedQuery(name = "User.findAll", query = "select u from User u")
public class User {

    @Id
    @NotBlank(message = "Please enter a User Name")
    private String userName;

    @NotBlank(message = "Please enter a Password")
    private String password;

    @NotBlank
    private String email;

    @Column(name = "requested_supplier_role", nullable = true)
    private Boolean requestedSupplierRole;

    @Column(name = "isPassword_Changed", nullable = true)
    private boolean passwordChanged;

    /**
     * No arg constructor
     */
    public User() {
    }

    /**
     * constructor
     *
     * @param userName
     * @param password
     * @param email
     */
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.passwordChanged = true;
    }

    @ManyToMany
    @JoinTable(name = "SEC_USER_GROUPS",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();

    /**
     * Method to add user to a group
     *
     * @param g
     */
    public void addGroup(Group g) {
        this.groups.add(g);
        g.getUsers().add(this);
    }

    /**
     * Method to remove user from a group
     *
     * @param g
     */
    public void removeGroup(Group g) {
        this.groups.remove(g);
        g.getUsers().remove(this);
    }

    /**
     * Get the value of groups
     *
     * @return the value of groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Set the value of groups
     *
     * @param groups new value of groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param newPassword
     */
    public void setPassword(String newPassword) {
        // Check if the new password is actually different from the existing one
        if (this.password == null || !this.password.equals(newPassword)) {
            this.passwordChanged = true;
            this.password = newPassword;
        }
    }

    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of requestedSupplierRole
     *
     * @return the value of requestedSupplierRole
     */
    public Boolean getRequestedSupplierRole() {
        return requestedSupplierRole;
    }

    /**
     * Set the value of requestedSupplierRole
     *
     * @param requestedSupplierRole new value of requestedSupplierRole
     */
    public void setRequestedSupplierRole(Boolean requestedSupplierRole) {
        this.requestedSupplierRole = requestedSupplierRole;
    }

    /**
     * Get the value of passwordChaned
     *
     * @return the value of passwordChaned
     */
    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    /**
     * Set the value of passwordChaned
     *
     * @param passwordChanged
     */
    public void setPasswordChanged(boolean passwordChanged) {
        /*
        if (!password.equals(this.password)) {
            this.passwordChanged = true;
            this.password = password;
        }
        */
         this.passwordChanged = passwordChanged;
    }

    //To String Method
    /**
     * Group To String Method
     *
     * @return
     */
    @Override
    public String toString() {
        return "User{" + "name=" + userName + "Email =" + email + '}';
    }

}
