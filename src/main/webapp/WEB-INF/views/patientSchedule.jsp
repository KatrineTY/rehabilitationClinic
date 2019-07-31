<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 ml-auto mr-auto">
            <div class="card bg-custom mb-5">
                <table class="table table-hover">
                    <thead class="card-header">
                    <tr>
                        <td><b>Date</b></td>
                        <td><b>Kind of treatment</b></td>
                        <td><b>Name of treatment</b></td>
                        <td><b>Status</b></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty events}">
                        <c:forEach var="event" items="${events}">
                            <tr>
                                <td>${event.date}</td>
                                <td>${event.type.kind}</td>
                                <td>${event.type.name}</td>
                                <td>${event.status}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>