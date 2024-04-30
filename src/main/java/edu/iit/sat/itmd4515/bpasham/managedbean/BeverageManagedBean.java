/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.managedbean;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.service.BeverageService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class BeverageManagedBean {
    @Inject
    private BeverageService beverageService;

    // Fetch all beverages
    public List<Beverage> getAvailableBeverages() {
        return beverageService.findAll();
    }
}
