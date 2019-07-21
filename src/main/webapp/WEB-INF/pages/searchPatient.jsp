<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/static/css/pageTemplate.css">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 ml-auto mr-auto">
            <div class="card bg-custom mb-5">
                <input class="card-header form-control" type="text" placeholder="Search"
                       aria-label="Search">
                <table id="bookTable" class="table">
                    <tr>
                        <td>Patient name</td>
                        <td>Insurance</td>
                    </tr>
                    <c:if test="${not empty patients}">
                        <c:forEach var="patient" items="${patients}">
                            <tr>
                                <td>${patient.name}</td>
                                <td>${patient.insurance}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
