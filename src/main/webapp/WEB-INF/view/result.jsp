<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Calculation Result</title>
</head>
<body>
    <h2>Calculation Result</h2>

    <% 
        String error = (String) request.getAttribute("error");
        Double num1 = (Double) request.getAttribute("num1");
        Double num2 = (Double) request.getAttribute("num2");
        String operation = (String) request.getAttribute("operation");
        Double result = (Double) request.getAttribute("result");
    %>

    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } else { %>
        <p>Number 1: <%= num1 %></p>
        <p>Number 2: <%= num2 %></p>
        <p>Operation: <%= operation %></p>
        <p>Result: <strong><%= result %></strong></p>
    <% } %>

    <br>
    <a href="home">Go Back</a>
</body>
</html>
