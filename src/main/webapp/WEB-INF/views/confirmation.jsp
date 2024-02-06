<%-- 
    Document   : confirmation
    Created on : 05-Feb-2024, 10:41:44 pm
    Author     : bhanu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Page</title>
    </head>
    <body>
        <h1>Country confirmation Page</h1>
        
        <ul>
            <li>${requestScope.country.code}</li>
            <li>${requestScope.country.name}</li>
            <li>${requestScope.country.indepYear}</li>
            <li>${requestScope.country.population}</li>
            <li>${requestScope.country.capital}</li>
        </ul>
    </body>
</html>
