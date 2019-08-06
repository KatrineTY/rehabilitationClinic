<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="../resources/static/js/prescriptions.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 ml-auto mr-auto">
            <div class="card bg-custom mb-5">
                <h3 class="card-header">Patient</h3>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-8 ml-auto mr-auto">
                            <form:form method="post" modelAttribute="prescriptionInfo"
                                       action="${prescriptionInfo.prescription.id}">
                                <input style="display:none" name="prescription.id"
                                       value="${prescriptionInfo.prescription.id}" type="number">
                                <input style="display:none" name="prescription.responsibleDoctor.id" type="number"
                                       value="${prescriptionInfo.prescription.responsibleDoctor.id}">
                                <div class="form-group">
                                    <label for="patient.name">Patient name</label>
                                    <form:errors path="prescription.patient.name" cssClass="text-danger"/>
                                    <input name="prescription.patient.name" type="text" class="form-control"
                                           id="patient.name"
                                           value="${prescriptionInfo.prescription.patient.name}"/>
                                </div>
                                <div class="form-group">
                                    <label for="type.name">Procedure/Medicament</label>
                                    <select name="prescription.type.kind" class="form-control" id="type.kind">
                                        <option value="Procedure"
                                                onclick="hideDose()"
                                            ${prescriptionInfo.prescription.type.kind=='Procedure'?'selected':'none'}>

                                            Procedure
                                        </option>
                                        <option value="Medicament" onclick="showDose()"
                                            ${prescriptionInfo.prescription.type.kind=='Medicament'?'selected':'none'}>

                                            Medicament
                                        </option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="type.name">Name</label>
                                    <input name="prescription.type.name" type="text" class="form-control" id="type.name"
                                           value="${prescriptionInfo.prescription.type.name}"/>
                                </div>
                                <div class="form-group" id="dose-group"
                                    ${prescriptionInfo.prescription.type.kind=='Procedure'?'style="display: none;"':''} >
                                    <label for="dose">Dose</label>
                                    <form:errors path="prescription.dose" cssClass="text-danger"/>
                                    <input name="prescription.dose" type="text" class="form-control" id="dose"
                                           value="${prescriptionInfo.prescription.dose}"/>
                                </div>
                                <div class="form-group">
                                    <label>Period</label>
                                    <div class="input-group">
                                        <form:errors path="prescription.startDate" cssClass="text-danger"/>
                                        <input name="prescription.startDate" type="date" class="form-control"
                                               value="${prescriptionInfo.prescription.startDate}">
                                        <form:errors path="prescription.endDate" cssClass="text-danger"/>
                                        <input name="prescription.endDate" type="date" class="form-control"
                                               value="${prescriptionInfo.prescription.endDate}">
                                    </div>
                                </div>
                                <div class="times">
                                    <label for="time0">Times:</label>
                                    <form:errors path="prescriptionTimes[0].time" cssClass="text-danger"/>

                                    <c:if test="${not empty prescriptionInfo.prescriptionTimes}">
                                        <c:forEach var="time" items="${prescriptionInfo.prescriptionTimes}"
                                                   varStatus="loop">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <input style="display:none"
                                                           name="prescriptionTimes[${loop.index}].id"
                                                           value="${prescriptionInfo.prescriptionTimes[loop.index].id}">
                                                    <input name="prescriptionTimes[${loop.index}].time" type="time"
                                                           class="form-control"
                                                           id="time${loop.index}"
                                                           value="${time.time}"/>
                                                    <div class="input-group-append">
                                                        <button class="btn btn-primary" type="button"
                                                                onclick="addPrescriptionTime()">
                                                            +
                                                        </button>
                                                        <c:if test="${loop.index != 0}">
                                                            <button class="btn btn-danger" type="button"
                                                                    onclick="removePrescriptionTime(this)">
                                                                ×
                                                            </button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                                <button type="submit" class="btn btn-primary">Save prescription</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function hideDose() {
        $("#dose-group").hide();

    }

    function showDose() {
        $("#dose-group").show();
    }
</script>
</body>
</html>
