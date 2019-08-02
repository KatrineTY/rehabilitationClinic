<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="../resources/static/js/prescriptions.js"></script>
</head>
<body>
<form:form method="post" modelAttribute="prescriptionInfo" action="../edit-prescription">
    <input style="display:none" name="prescription.id" value="${prescriptionInfo.prescription.id}">
    <input style="display:none" name="prescription.responsibleDoctor.id"
           value="${prescriptionInfo.prescription.responsibleDoctor.id}">
    <input style="display:none" name="prescription.version" value="${prescriptionInfo.prescription.version}">
    <div class="form-group">
        <label for="patient.name">Patient name</label>
        <input name="prescription.patient.name" type="text" class="form-control" id="patient.name"
               value="${prescriptionInfo.prescription.patient.name}"/>
    </div>
    <div class="form-group">
        <label for="type.kind">Type</label>
        <input name="prescription.type.kind" type="text" class="form-control" id="type.kind"
               value="${prescriptionInfo.prescription.type.kind}"/>
    </div>
    <div class="form-group">
        <label for="type.name">Name</label>
        <input name="prescription.type.name" type="text" class="form-control" id="type.name"
               value="${prescriptionInfo.prescription.type.name}"/>
    </div>
    <div class="form-group">
        <label>Period</label>
        <div class="input-group">
            <input name="prescription.startDate" type="date" class="form-control"
                   value="${prescriptionInfo.prescription.startDate}">
            <input name="prescription.endDate" type="date" class="form-control"
                   value="${prescriptionInfo.prescription.endDate}">
        </div>
    </div>
    <div class="times">
        <div class="form-group">
            <label for="time0">Times:</label>
            <c:if test="${not empty prescriptionInfo.prescriptionTimes}">
                <c:forEach var="time" items="${prescriptionInfo.prescriptionTimes}" varStatus="loop">
                    <input style="display:none" name="prescriptionTimes[${loop.index}].id"
                           value="${prescriptionInfo.prescriptionTimes[loop.index].id}">
                    <div class="input-group">
                        <input name="prescriptionTimes[${loop.index}].time" type="time" class="form-control"
                               id="time${loop.index}"
                               value="${time.time}"/>
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button" onclick="addPrescriptionTime()">
                                +
                            </button>
                            <c:if test="${loop.index != 0}">
                                <button class="btn btn-danger" type="button" onclick="removePrescriptionTime(this)">
                                    ×
                                </button>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <div class="form-group">
        <label for="dose">Dose</label>
        <input name="prescription.dose" type="text" class="form-control" id="dose"
               value="${prescriptionInfo.prescription.dose}"/>
    </div>
    <button type="submit" class="btn btn-primary">Save prescription</button>
</form:form>
</body>
</html>
