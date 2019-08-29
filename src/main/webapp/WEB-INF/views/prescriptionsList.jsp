<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/static/js/search.js"></script>

    <title>Prescriptions</title>
</head>
<body>
<t:page width="10">
    <jsp:body>
        <input class=" form-control" type="text" placeholder="Search"
               aria-label="Search" onkeyup="search()" id="searchByName">
        <table id="patientTable" class="table table-hover">
            <thead class="card-header">
            <tr>
                <td><b>Patient name</b></td>
                <td><b>Type</b></td>
                <td><b>Name</b></td>
                <td><b>Dose</b></td>
                <td><b>Start date</b></td>
                <td><b>End date</b></td>
                <security:authorize access="hasRole('ROLE_DOCTOR')">
                    <td>Edit</td>
                    <td>Delete</td>
                </security:authorize>
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
                        <security:authorize access="hasRole('ROLE_DOCTOR')">
                            <td><a href="edit-prescription/${prescriptionInfo.prescription.id}">Edit</a></td>
                            <td>
                                <form action="delete-prescription" method="post">
                                    <input style="display: none" type="number"
                                           value="${prescriptionInfo.prescription.id}" name="id"/>
                                    <button type="submit" class="btn btn-primary">Delete</button>
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
