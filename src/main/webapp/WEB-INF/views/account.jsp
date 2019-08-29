<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <title>Account</title>
</head>
<t:loading/>
<t:page>
    <jsp:body>
        <h3 class="card-header">${empName}</h3>
        <div class="card-body">
            <div class="list-group">
                <security:authorize access="hasRole('ROLE_MAIN_DOCTOR')">
                    <a href="add-patient" class="list-group-item list-group-item-action">Add patient</a>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_DOCTOR')">
                    <a href="add-prescription" class="list-group-item list-group-item-action">Add prescription</a>
                </security:authorize>
                <a href="patients-list" class="list-group-item list-group-item-action">Patients list</a>
                    <%--                <security:authorize access="hasAnyRole('MAIN_DOCTOR', 'DOCTOR')">--%>
                <a href="get-prescriptions-list" class="list-group-item list-group-item-action">Prescriptions
                    list</a>
                    <%--                </security:authorize>--%>
                <a href="get-events-list/1" class="list-group-item list-group-item-action">Events list</a>
                <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                    <a href="add-employee" class="list-group-item list-group-item-action">Add employee</a>
                    <a href="get-employees-list" class="list-group-item list-group-item-action">Employees list</a>
                </security:authorize>
            </div>
        </div>
    </jsp:body>
</t:page>

