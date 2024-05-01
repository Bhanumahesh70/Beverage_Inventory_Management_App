/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import edu.iit.sat.itmd4515.bpasham.domain.Order;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import edu.iit.sat.itmd4515.bpasham.domain.BeverageType;
import edu.iit.sat.itmd4515.bpasham.domain.Supplier;
import edu.iit.sat.itmd4515.bpasham.service.BeverageService;
import edu.iit.sat.itmd4515.bpasham.service.CustomerService;
import edu.iit.sat.itmd4515.bpasham.service.OrderService;
import edu.iit.sat.itmd4515.bpasham.service.SupplierService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@Named
@RequestScoped
public class PlaceOrderController {

    @EJB
    private OrderService orderService;

    @EJB
    private BeverageService beverageService;

    @EJB
    private SupplierService supplierService;

    @EJB
    private CustomerService customerService;

    @Inject
    CustomerWelcomeController cwc;

    private Map<Beverage, Integer> quantityMap;

    private List<Beverage> availableBeverages;

    private List<Supplier> availableSuppliers;

    //private Map<BeverageType, List<Beverage>> beveragesByType;
    private Map<BeverageType, List<Beverage>> beveragesByType;

    private Supplier selectedSupplier;

    private static final Logger LOG = Logger.getLogger(PlaceOrderController.class.getName());

    @PostConstruct
    public void postConstruct() {

        LOG.info("Inside PlaceOrderController.postConstruct");
        quantityMap = new HashMap<>();
        beveragesByType = new LinkedHashMap<>();
        availableBeverages = beverageService.findAll();
        availableSuppliers = supplierService.findAll();
        for (Beverage beverage : availableBeverages) {
            quantityMap.put(beverage, 0); // Initialize quantities to 0
            BeverageType type = beverage.getType();
            beveragesByType.computeIfAbsent(type, k -> new ArrayList<>()).add(beverage);
        }
    }
    
    

    public String placeOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setSupplier(selectedSupplier);
        order.setCustomer(cwc.getCustomer());
        List<Beverage> selectedBeverages = new ArrayList<>();
        int totalQuantity = 0;

        for (Map.Entry<Beverage, Integer> entry : quantityMap.entrySet()) {
            Beverage beverage = entry.getKey();
            Integer quantity = entry.getValue();
            if (quantity != null && quantity > 0) {

                LOG.info("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii selected beverage is: " + beverage.toString());

                totalQuantity += quantity;
                order.addOrderBeverageDetails(beverage, quantity);
                //order.addBeverage(beverage);
                selectedBeverages.add(beverage);
            }
        }
        order.setQuantity(totalQuantity);
        // Save the order
        LOG.log(Level.INFO, "saveOrder has been invoked with model: {0}", order.toString());
        // orderService.placeOrder(order);
        //orderService.placeNewOrder(order, cwc.getCustomer(), selectedBeverages);     
        //customerService.createOrderForCustomer(cwc.getCustomer(), order);
        // orderService.placeNewOrder(order);
        orderService.placeNewOrder(order, selectedBeverages);
        LOG.info("saveOrder after calling service layer: " + order.toString());

        cwc.refreshCustomerModel();
        return "/customer/welcome.xhtml";
    }

    /**
     * Get the value of availableBeverages
     *
     * @return the value of availableBeverages
     */
    public List<Beverage> getAvailableBeverages() {
        return availableBeverages;
    }

    /**
     * Set the value of availableBeverages
     *
     * @param availableBeverages new value of availableBeverages
     */
    public void setAvailableBeverages(List<Beverage> availableBeverages) {
        this.availableBeverages = availableBeverages;
    }

    /**
     * Get the value of quantityMap
     *
     * @return the value of quantityMap
     */
    public Map<Beverage, Integer> getQuantityMap() {
        return quantityMap;
    }

    /**
     * Set the value of quantityMap
     *
     * @param quantityMap new value of quantityMap
     */
    public void setQuantityMap(Map<Beverage, Integer> quantityMap) {
        this.quantityMap = quantityMap;
    }

    /**
     * Get the value of availableSuppliers
     *
     * @return the value of availableSuppliers
     */
    public List<Supplier> getAvailableSuppliers() {
        return availableSuppliers;
    }

    /**
     * Set the value of availableSuppliers
     *
     * @param availableSuppliers new value of availableSuppliers
     */
    public void setAvailableSuppliers(List<Supplier> availableSuppliers) {
        this.availableSuppliers = availableSuppliers;
    }

    /**
     * Get the value of selectedSupplier
     *
     * @return the value of selectedSupplier
     */
    public Supplier getSelectedSupplier() {
        return selectedSupplier;
    }

    /**
     * Set the value of selectedSupplier
     *
     * @param selectedSupplier new value of selectedSupplier
     */
    public void setSelectedSupplier(Supplier selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    /**
     * Get the value of beveragesByType
     *
     * @return the value of beveragesByType
     */
    public Map<BeverageType, List<Beverage>> getBeveragesByType() {
        return beveragesByType;
    }

    /**
     * Set the value of beveragesByType
     *
     * @param beveragesByType new value of beveragesByType
     */
    public void setBeveragesByType(Map<BeverageType, List<Beverage>> beveragesByType) {
        this.beveragesByType = beveragesByType;
    }

}
