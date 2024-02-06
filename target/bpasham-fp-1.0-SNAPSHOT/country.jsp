<%-- 
    Document   : country
    Created on : 05-Feb-2024, 8:22:48 pm
    Author     : bhanu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Country</title>
    </head>
    <body>
        <h1>Create a new Sakila database Country</h1>
        <form method = "post" action="/bpasham-fp/country">
            
            <div>
                <label for='countryCodeId'>Country Code:</label>
            <input type ='text' id ='countryCodeId' name='countryCode'/>
            </div>
            
            <div>
                <label for='countryNameId'>Country Name:</label>
            <input type ='text' id ='countryNameId' name='countryName'/>
            </div>
            
            <div>
                <label for='continentId'>Continent:</label>
            <input type ='text' id ='continentId' name='continent'/>
            </div>
            
            <div>
                <label for='independentYearId'>Independent Year:</label>
            <input type ='number' id ='independentYearId' name='independentYear'/>
            </div>
            
             <div>
                <label for='populationId'>Population:</label>
            <input type ='text' id ='population' name='population'/>
            </div>
            
             <div>
                <label for='capitalId'>capital:</label>
            <input type ='text' id ='capitalId' name='capital'/>
            </div>
            
            <button type="submit">create button</button>
        </form>
    </body>
</html>
