<%-- 
    Document   : newjsp
    Created on : 05-Feb-2024, 2:11:30 pm
    Author     : bhanu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2>${pageContext.request.servletContext.contextPath}</h2>
        <FORM ACTION="/bpasham-fp/demo" method="post">
            First Parameter: <INPUT TYPE="TEXT" NAME="param1"><BR>
            Second Parameter: <INPUT TYPE="TEXT" NAME="param2"><BR>
            Third Parameter: <INPUT TYPE="TEXT" NAME="param3"><BR>
            <CENTER><INPUT TYPE="SUBMIT"></CENTER>
        </FORM>
    </body>
</html>
