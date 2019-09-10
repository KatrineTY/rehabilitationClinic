<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css"
          type="text/css">
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.easy-autocomplete.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/js/easy-autocomplete.min.css">
    <title>Events</title>
</head>
<body>
<t:loading/>
<t:page width="14">
    <jsp:body>
        <form method="post">
            <div class="form-group">
                <div class="input-group">
                    <input required class=" form-control" type="text" placeholder="search by patient's name"
                           name="searchPatientName" value="${searchPatientName}" id="name"/>
                    <input required class=" form-control" type="date" placeholder="search by date"
                           name="searchStartDate"
                           value="${searchStartDate}"/>
                    <input required class=" form-control" type="date" placeholder="search by date" name="searchEndDate"
                           value="${searchEndDate}"/>
                    <button type="submit" class="btn btn-primary" formaction="generate-report">Search</button>
                </div>
            </div>
            <c:if test="${not empty events}">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" formaction="generate-report/pdf">
                        Generate pdf report
                    </button>
                </div>
            </c:if>
        </form>
        <table id="patientTable" class="table table-hover">
            <thead class="card-header">
            <tr>
                <td><b>Date</b></td>
                <td><b>Kind of treatment</b></td>
                <td><b>Name of treatment</b></td>
                <td><b>Dose</b></td>
                <td><b>Building</b></td>
                <td><b>Ward</b></td>
                <td><b>Status</b></td>
                <td><b>Comment</b></td>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty events}">
                <c:forEach var="event" items="${events}">
                    <tr>
                        <td><javatime:format value="${event.date}" style="MS"/></td>
                        <td>${event.type.kind}</td>
                        <td>${event.type.name}</td>
                        <td>${event.dose}</td>
                        <td>${event.building}</td>
                        <td>${event.ward}</td>
                        <td>${event.status}</td>
                        <td>${event.comment}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

        </div>
    </jsp:body>
</t:page>

<script>

    var patientNameOptions = {
        url: "/RehabilitationClinic/getPatients",
        list: {
            maxNumberOfElements: 8,
            match: {
                enabled: true
            },
            sort: {
                enabled: true
            }
        },
        requestDelay: 300
    };
    $("#name").easyAutocomplete(patientNameOptions);
</script>
</body>
</html>
