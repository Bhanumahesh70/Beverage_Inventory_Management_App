/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;
import edu.iit.sat.itmd4515.bpasham.domain.Beverage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 *
 * @author bhanu
 */
public class BeanValidationTestForBeverage {
    private static Validator validator;

    @BeforeAll
    public static void beforeAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void validateNameNotBlank() {
        Beverage beverage = new Beverage("", LocalDate.of(2025, 3, 5), "true", BeverageType.SODA);
        Set<ConstraintViolation<Beverage>> violations = validator.validate(beverage);
        assertEquals(1, violations.size(), "Expected one violation for blank name");
    }

    @Test
    public void validateExpiryDateNotNull() {
        Beverage beverage = new Beverage("Coca Cola", null, "true", BeverageType.SODA);
        Set<ConstraintViolation<Beverage>> violations = validator.validate(beverage);
        assertEquals(1, violations.size(), "Expected one violation for null expiry date");
    }

    @Test
    public void validateIsNonAlcoholicPattern() {
        Beverage beverage = new Beverage("Coca Cola", LocalDate.of(2025, 3, 5), "yes", BeverageType.SODA);
        Set<ConstraintViolation<Beverage>> violations = validator.validate(beverage);
        assertEquals(1, violations.size(), "Expected one violation for invalid isNonAlcoholic value");
    }

    @Test
    public void validateBeverageTypeNotNull() {
        Beverage beverage = new Beverage("Coca Cola", LocalDate.of(2025, 3, 5), "true", null);
        Set<ConstraintViolation<Beverage>> violations = validator.validate(beverage);
        assertEquals(1, violations.size(), "Expected one violation for null beverage type");
    }

    @Test
    public void validateValidBeverage() {
        Beverage beverage = new Beverage("Coca Cola", LocalDate.of(2025, 3, 5), "true", BeverageType.SODA);
        Set<ConstraintViolation<Beverage>> violations = validator.validate(beverage);
        assertEquals(0, violations.size(), "Expected no violations for valid beverage");
    }
}
