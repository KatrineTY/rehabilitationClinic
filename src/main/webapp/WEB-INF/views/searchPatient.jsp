<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/static/css/pageTemplate.css">
    <script src="./resources/static/js/searchPatient.js"></script>
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 ml-auto mr-auto">
            <div class="card bg-custom mb-5">
                <input class=" form-control" type="text" placeholder="Search"
                       aria-label="Search" onkeyup="searchPatient()" id="searchPatient">
                <table id="patientTable" class="table table-hover">
                    <thead class="card-header">
                    <tr>
                        <td><b>Patient name</b></td>
                        <td><b>Status</b></td>
                        <td><b>Building</b></td>
                        <td><b>Ward</b></td>
                        <td>Schedule</td>
                        <td>Info</td>
                        <td>Discharge</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty patientCards}">
                        <c:forEach var="card" items="${patientCards}">
                            <tr>
                                <td>${card.patient.name}</td>
                                <td>${card.status}</td>
                                <td>${card.building}</td>
                                <td>${card.ward}</td>
                                <td><a href="patient-schedule/${card.patient.id}">Show</a></td>
                                <td><a href="edit-patient/${card.patient.id}">Edit</a></td>
                                <td>
                                    <form action="discharge-patient" method="post">
                                        <input style="display: none" type="number" value="${card.patient.id}"
                                               name="id"/>
                                        <button type="submit" class="btn btn-primary">Discharge</button>
                                    </form>
                                </td>
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
