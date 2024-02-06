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
        
        <c:if test="${not empty violations}">
            <h2>Constraint Violations</h2>
            <table border=1>
                <tr>
                    <th>Name</th>
                    <th>Value</th>
                </tr>
                <c:forEach var="v" items="${requestScope.violations}">
                    <tr>
                        <td><c:out value="${v.propertyPath}" /></td>
                        <td><c:out value="${v.message}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        
        
        <form method = "post" action="/bpasham-fp/country">
            
            <div>
                <label for='countryCodeId'>Country Code:</label>
                <input type ='text' id ='countryCodeId' name='countryCode' value="${requestScope.country.code}"/>
            </div>
            
            <div>
                <label for='countryNameId'>Country Name:</label>
            <input type ='text' id ='countryNameId' name='countryName' value="${requestScope.country.name}"/>
            </div>
            
            <div>
                <label for='continentId'>Continent:</label>
            <input type ='text' id ='continentId' name='continent' value="${requestScope.country.continent}"/>
            </div>
            
            <div>
                <label for='independentYearId'>Independent Year:</label>
            <input type ='number' id ='independentYearId' name='independentYear' value="${requestScope.country.indepYear}"/>
            </div>
            
             <div>
                <label for='populationId'>Population:</label>
            <input type ='text' id ='population' name='population' value="${requestScope.country.population}"/>
            </div>
            
             <div>
                <label for='capitalId'>capital:</label>
            <input type ='text' id ='capitalId' name='capital' value="${requestScope.country.capital}"/>
            </div>
            
            <button type="submit">create button</button>
        </form>
    </body>
</html>
