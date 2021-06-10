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
        <th>Model</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${buses}" var="book">
        <tr>
            <td>${buses.model}</td>
            <td>${buses.transportTypeDto}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>