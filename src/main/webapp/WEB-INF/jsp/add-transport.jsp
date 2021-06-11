<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Add transport</title>
</head>
<body>
<form:form action="/transport/add" method="post" modelAttribute="transport">
    <label for="model">Model:</label>
    <input type="text" id="model" name="model">
    <br>
    <form:label path="type">Choose Type:</form:label>
    <form:select path="type">
        <c:forEach items="${types}" var="type">
            <option value="${type.description}">${type.description}</option>
        </c:forEach>
    </form:select>
    <br>
    <form:label path="driveType">Drive Type:</form:label>
    <form:input type="text" path="driveType"/>
    <br>
    <form:label path="seatNum">Seat Number:</form:label>
    <form:input type="number" path="seatNum"/>
    <br>
    <form:label path="carNum">Car Number:</form:label>
    <form:input type="number" path="carNum"/>
    <br>
    <form:label path="route">Choose route:</form:label>
    <form:select path="route">
        <c:forEach items="${routes}" var="routeNumber">
            <option value="${routeNumber}">${routeNumber}</option>
        </c:forEach>
    </form:select>
    <br>
    <input type="submit" name="Add">
</form:form>
</body>
</html>