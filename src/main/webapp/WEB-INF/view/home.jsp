<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
    <h2>Simple Calculator</h2>
    <form action="calculate" method="post">
        <label>Enter Number 1:</label>
        <input type="text" name="num1" required>
        <br><br>
        <label>Enter Number 2:</label>
        <input type="text" name="num2" required>
        <br><br>
        <label>Select Operation:</label>
        <select name="operation">
            <option value="add">Addition (+)</option>
            <option value="sub">Subtraction (-)</option>
            <option value="mul">Multiplication (*)</option>
            <option value="div">Division (/)</option>
        </select>
        <br><br>
        <button type="submit">Calculate</button>
    </form>
</body>
</html>
