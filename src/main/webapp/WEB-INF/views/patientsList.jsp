<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/static/js/searchPatient.js"></script>

    <title>Patients</title>
</head>
<body>
<t:page width="10">
    <jsp:body>
        <input class=" form-control" type="text" placeholder="Search"
               aria-label="Search" onkeyup="searchPatient()" id="searchPatient">
        <table id="patientTable" class="table table-hover">
            <thead class="card-header">
            <tr>
                <td><b>Patient name</b></td>
                <td><b>Insurance</b></td>
                <td><b>Status</b></td>
                <td><b>Building</b></td>
                <td><b>Ward</b></td>
                <td>Schedule</td>
                <security:authorize access="hasRole('ROLE_MAIN_DOCTOR')">
                    <td>Info</td>
                    <td>Discharge</td>
                </security:authorize>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty patientCards}">
                <c:forEach var="card" items="${patientCards}">
                    <tr>
                        <td>${card.patient.name}</td>
                        <td>${card.patient.insurance}</td>
                        <td>${card.status}</td>
                        <td>${card.building}</td>
                        <td>${card.ward}</td>
                        <td><a href="patient-schedule/${card.patient.id}">Show</a></td>
                        <security:authorize access="hasRole('ROLE_MAIN_DOCTOR')">
                            <td><a href="edit-patient/${card.patient.id}">Edit</a></td>
                            <td>
                                <form action="discharge-patient" method="post">
                                    <input style="display: none" type="number" value="${card.patient.id}"
                                           name="id"/>
                                    <button type="submit" class="btn btn-primary"
                                        ${card.status=='Discharged'?'disabled':'none'}>Discharge
                                    </button>
                                </form>
                            </td>
                        </security:authorize>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </jsp:body>
</t:page>

</body>
</html>
