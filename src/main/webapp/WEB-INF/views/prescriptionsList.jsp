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
                        <td><b>Type</b></td>
                        <td><b>Name</b></td>
                        <td><b>Dose</b></td>
                        <td><b>Start date</b></td>
                        <td><b>End date</b></td>
                        <td>Edit</td>
                        <td>Delete</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty prescriptions}">
                        <c:forEach var="prescriptionInfo" items="${prescriptions}">
                            <tr>
                                <td>${prescriptionInfo.prescription.patient.name}</td>
                                <td>${prescriptionInfo.prescription.type.kind}</td>
                                <td>${prescriptionInfo.prescription.type.name}</td>
                                <td>${prescriptionInfo.prescription.dose}</td>
                                <td>${prescriptionInfo.prescription.startDate}</td>
                                <td>${prescriptionInfo.prescription.endDate}</td>
                                <td><a href="edit-prescription/${prescriptionInfo.prescription.id}">Edit</a></td>
                                <td><a href="delete-prescription/${prescriptionInfo.prescription.id}">Delete</a></td>
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
