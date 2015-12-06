<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>
<h1 style="color:#d2691e">Add meal</h1>

<form method="POST" action="meals?action=newMeal">
    Date: <input type="text" name="date" value="${userMeal.getDateTime()}"/> Вводить в формате (2015-05-30T10:00)<br>
    Description: <input type="text" name="descr" value="${userMeal.getDescription()}"/><br>
    Calories: <input type="text" name="calory" value="${userMeal.getCalories()}"/><br>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
