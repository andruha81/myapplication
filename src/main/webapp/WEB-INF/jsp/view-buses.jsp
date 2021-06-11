<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>View Buses</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Type</th>
        <th>Model</th>
        <th>Drive Type</th>
        <th>Seat number</th>
        <th>Route</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${buses}" var="bus">
        <tr>
            <td>${bus.type}</td>
            <td>${bus.model}</td>
            <td>${bus.driveType}</td>
            <td>${bus.seatNum}</td>
            <td>${bus.route}</td>
            <td><a href="/transport/edit"></a>EDIT</td>
            <td><a href="/transport/delete">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>