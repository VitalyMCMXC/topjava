<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h1 style="color:#d2691e">Meal List</h1>

<%
    request.setAttribute("attr", request.getAttribute("attr"));
%>
<p> ${attr} <p/>

<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#1ee2ff">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${attr}" var="meal">
        <tr>
            <td><c:out value="${meal.getDateTime()}" /></td>
            <td><c:out value="${meal.getDescription()}" /></td>
            <td><c:out value="${meal.getCalories()}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
