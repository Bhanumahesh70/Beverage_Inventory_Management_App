/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.service;
import edu.iit.sat.itmd4515.bpasham.domain.Inventory;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author bhanu
 */
@Stateless
public class InventoryService extends AbstractService<Inventory>{
    public InventoryService() {
        super(Inventory.class);
    }
    
    
    public List<Inventory> findAll(){
        return super.findAll("Inventory.findAll");
    }
}
