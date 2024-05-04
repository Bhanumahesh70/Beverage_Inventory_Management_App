/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.security;

import edu.iit.sat.itmd4515.bpasham.security.Group;
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
@NamedQuery(name="User.findAll", query="select u from User u")
public class User {

    @Id
    @NotBlank(message="Please enter a User Name")
    private String userName;

    @NotBlank(message="Please enter a Password")
    private String password;

    @NotBlank
    private String email;
    
    public User() {
    }
    

    


    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    
    
    @ManyToMany
    @JoinTable(name="SEC_USER_GROUPS",
            joinColumns = @JoinColumn(name="USERNAME"),
            inverseJoinColumns = @JoinColumn(name="GROUPNAME"))
       private List<Group> groups = new ArrayList<>();
    
    public void addGroup(Group g ){
        this.groups.add(g);
        g.getUsers().add(this);
    }

    public void removeGroup(Group g ){
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
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
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
    
    //To String Method
    @Override
    public String toString() {
        return "User{" +  "name=" + userName + "Email ="+email + '}';
    }

}
