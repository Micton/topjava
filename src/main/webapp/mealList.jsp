<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meals List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meals list</h2>
<table class="table table-hover table-bordered">
    <thead style="background-color: #bce8f1;">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <c:forEach items="${mealList}" var="meal">
        <c:choose>
            <c:when test="${meal.exceed}">
                <c:set var="col" scope="page" value="#DC143C"/>
            </c:when>
            <c:otherwise>
                <c:set var="col" scope="page" value="#008000"/>
            </c:otherwise>
        </c:choose>
        <tbody>
        <tr style="color: ${col}">
            <th><c:out value="${f:formatDateTime(meal.dateTime, 'dd.MM.yyyy HH:mm')}"/></th>
            <th><c:out value="${meal.description}"/></th>
            <th><c:out value="${meal.calories}"/></th>
            <th><c:out value="Update"/></th>
            <th><c:out value="Delete"/></th>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>
