<%@tag description="Navigation bar template" pageEncoding="UTF-8" %>
<%@taglib prefix="navi" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item ${pageContext.request.requestURI.endsWith('account.jsp')?'active':''}">
                <a class="nav-link" href="<c:url value="/account"/>">Account </a>
            </li>
            <li class="nav-item ${pageContext.request.requestURI.endsWith('addPatient.jsp')?'active':''}">
                <a class="nav-link " href="<c:url value="/add-patient"/>">Add patient</a>
            </li>
            <li class="nav-item" ${pageContext.request.requestURI.endsWith('addPrescription.jsp')?'active':''}>
                <a class="nav-link" href="<c:url value="/add-prescription"/>">Add prescription</a>
            </li>
            <li class="nav-item ${pageContext.request.requestURI.endsWith('patientsList.jsp')?'active':''}">
                <a class="nav-link" href="<c:url value="/patients-list"/>">Patients list</a>
            </li>
            <li class="nav-item ${pageContext.request.requestURI.endsWith('prescriptionsList.jsp')?'active':''}">
                <a class="nav-link" href="<c:url value="/get-prescriptions-list"/>">Prescriptions list</a>
            </li>
            <li class="nav-item ${pageContext.request.requestURI.endsWith('eventsList.jsp')?'active':''}">
                <a class="nav-link" href="<c:url value="/get-events-list/1"/>">Events list</a>
            </li>
        </ul>
    </div>
</nav>