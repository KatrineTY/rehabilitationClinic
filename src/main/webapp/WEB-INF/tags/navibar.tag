<%@tag description="Navigation bar template" pageEncoding="UTF-8" %>
<%@taglib prefix="navi" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ${pageContext.request.requestURI.endsWith('account.jsp')?'active':''}">
                <a class="nav-link" href="<c:url value="/account"/>">Account </a>
            </li>
            <security:authorize access="hasRole('ROLE_MAIN_DOCTOR')">
                <li class="nav-item ${pageContext.request.requestURI.endsWith('addPatient.jsp')?'active':''}">
                    <a class="nav-link" href="<c:url value="/add-patient"/>">Add patient</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_DOCTOR')">
                <li class="nav-item" ${pageContext.request.requestURI.endsWith('addPrescription.jsp')?'active':''}>
                    <a class="nav-link" href="<c:url value="/add-prescription"/>">Add prescription</a>
                </li>
            </security:authorize>
            <li class="nav-item ${pageContext.request.requestURI.endsWith('patientsList.jsp')?'active':''}">
                <a class="nav-link" href="<c:url value="/patients-list"/>">Patients list</a>
            </li>
            <%--            <security:authorize access="hasAnyRole('MAIN_DOCTOR', 'DOCTOR')">--%>
            <li class="nav-item ${pageContext.request.requestURI.endsWith('prescriptionsList.jsp')?'active':''}">
                <a class="nav-link" href="<c:url value="/get-prescriptions-list"/>">Prescriptions list</a>
            </li>
            <%--            </security:authorize>--%>
            <li class="nav-item ${pageContext.request.requestURI.endsWith('eventsList.jsp')?'active':''}">
                <a class="nav-link" href="<c:url value="/get-events-list/1"/>">Events list</a>
            </li>

            <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                <li class="nav-item ${pageContext.request.requestURI.endsWith('addEmployee.jsp')?'active':''}">
                    <a class="nav-link" href="<c:url value="/add-employee"/>">Add employee</a>
                </li>
                <li class="nav-item ${pageContext.request.requestURI.endsWith('employeesList.jsp')?'active':''}">
                    <a class="nav-link" href="<c:url value="/get-employees-list"/>">Employees list</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_NURSE')">
                <li class="nav-item ${pageContext.request.requestURI.endsWith('takenEvents.jsp')?'active':''}">
                    <a class="nav-link" href="<c:url value="/taken-events"/>">Taken events</a>
                </li>
            </security:authorize>
        </ul>
        <ul class="navbar-nav navbar-right">
            <li class="nav-item ">
                <a class="nav-link" href="<c:url value="/logout"/>">Logout</a>
            </li>
        </ul>
    </div>
</nav>