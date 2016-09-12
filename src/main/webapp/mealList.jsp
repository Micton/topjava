<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meals List</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meals list</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${mealList}" var="meal">
        <c:choose>
            <c:when test="${meal.exceed}">
                <c:set var="col" scope="page" value="#DC143C"/>
            </c:when>
            <c:otherwise>
                <c:set var="col" scope="page" value="#008000"/>
            </c:otherwise>
        </c:choose>
        <tr style="color: ${col}">
            <th><c:out value="${f:matches(meal.dateTime, 'dd.MM.yyyy')}"/></th>
            <th><c:out value="${meal.description}"/></th>
            <th><c:out value="${meal.calories}"/></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
