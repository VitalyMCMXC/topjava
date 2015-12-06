<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h1 style="color:#d2691e">Meal List</h1>

<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#1ee2ff">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mealList}" var="entry">
        <tr>
            <c:forEach items="${entry.value}" var="umwe">
                <c:url var="editUrl" value="meals?action=edit&date=${umwe.getDateTime()}&descr=${umwe.getDescription()}&calory=${umwe.getCalories()}" />
                <c:url var="deleteUrl" value="meals?action=delete&date=${umwe.getDateTime()}&descr=${umwe.getDescription()}" />
                <td><c:out value="${umwe.getDateTime()}" /></td>
                <td><c:out value="${umwe.getDescription()}" /></td>
                <td><c:out value="${umwe.getCalories()}" /></td>
                <td><a href="${editUrl}">Edit</a></td>
                <td><a href="${deleteUrl}">Delete</a></td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<p><a href="meal.jsp">Add Meal</a></p>
<br>
<form method="POST" action="meals?action=filter">
    From date: <input type="text" name="date1" />
    To date: <input type="text" name="date2" />
    <input type="submit" value="Submit"/>
    <p>Вводить в формате (2015-05-30T10:00)</p>
</form>
</body>
</html>
