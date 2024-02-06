/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.web;
import edu.iit.sat.itmd4515.bpasham.domain.*;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author bhanu
 */
@WebServlet(name = "CountryServlet", urlPatterns = {"/country","/Country","/c"})
public class CountryServlet extends HttpServlet{
    
    @Resource
    Validator validator;

    private static final Logger LOG = Logger.getLogger(CountryServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         LOG.info("CountryServlet in doPost");
         String countryCodeParameter = req.getParameter("countryCode");
         String countryNameParameter = req.getParameter("countryName");
         String continentParameter = req.getParameter("continent");
         String independentYearParameter = req.getParameter("independentYear");
         String populationParameter = req.getParameter("population");
         String capitalParameter = req.getParameter("capital");
         
         LOG.info("countryCodeParameter\t\t"+countryCodeParameter );
          LOG.info(" countryNameParameter\t\t"+countryNameParameter );
           LOG.info("continentParameter\t\t"+ continentParameter);
            LOG.info(" independentYearParameter\t\t"+independentYearParameter );
            LOG.info(" populationParameter\t\t"+populationParameter );
            LOG.info(" capitalParameter\t\t"+capitalParameter );
            
           
            
             int independentYear =0;
            int population=0;
            int capital=0;
            
            if(independentYearParameter!=null && !independentYearParameter.isBlank()){
                independentYear = Integer.parseInt(independentYearParameter);
            }
          
            if(populationParameter!=null && !populationParameter.isBlank()){
                try{
                population = Integer.parseInt(populationParameter);
                }catch(Exception E){
                    LOG.info("Enter a population number");
                }
            }
            if(capitalParameter!=null && !capitalParameter.isBlank()){
                try{
                capital = Integer.parseInt(capitalParameter);
                }catch(Exception E){
                    LOG.info("Enter a valid capital number");
                }
            }
            
            //Building country POJO
            Country c= new Country(countryCodeParameter,countryNameParameter,continentParameter,independentYear,population,capital);
        Set<ConstraintViolation<Country>> violations = validator.validate(c);
        for(ConstraintViolation<Country> violation : violations){
            LOG.info(violation.toString());
        }
        if (violations.size() > 0) {
            // invalid

            LOG.info("The entered country database has FAILED validation.  These are the violations:");
            
            for (ConstraintViolation<Country> violation : violations) {
                LOG.info(violation.toString());
            }

            req.setAttribute("country", c);
            req.setAttribute("violations", violations);
            RequestDispatcher rd = req.getRequestDispatcher("country.jsp");
            rd.forward(req, resp);

        } else {
            // valid
            LOG.info("The entered country database values PASSED validation");

            // create a customer in database if passes validation
            //createACustomer(c);
            
            req.setAttribute("country", c);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/confirmation.jsp");
            rd.forward(req, resp);
        }

            LOG.info("Country POJO data feilds after conversion"+c.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    LOG.info("CountryServlet in doGet");
    }
    
}
