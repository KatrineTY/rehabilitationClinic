<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/static/js/prescriptions.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.easy-autocomplete.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/doseHide.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/js/easy-autocomplete.min.css">

    <title>Edit prescription</title>

</head>
<body>
<t:page>
    <jsp:body>
        <h3 class="card-header">Prescription</h3>
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
                            <label for="name">Patient name</label>
                            <form:errors path="prescription.patient.name" cssClass="text-danger"/>
                            <form:input required="required" path="prescription.patient.name" type="text"
                                        class="form-control"
                                        id="name"
                                        value="${prescriptionInfo.prescription.patient.name}"/>
                        </div>
                        <div class="form-group">
                            <label for="type-kind">Procedure/Medicament</label>
                            <form:errors path="prescription.type.kind" cssClass="text-danger"/>

                            <form:select path="prescription.type.kind" class="form-control" id="type-kind">
                                <option value="Procedure"
                                        onclick="hideDose()"
                                    ${prescriptionInfo.prescription.type.kind=='Procedure'?'selected':'none'}>

                                    Procedure
                                </option>
                                <option value="Medicament" onclick="showDose()"
                                    ${prescriptionInfo.prescription.type.kind=='Medicament'?'selected':'none'}>

                                    Medicament
                                </option>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="type-name">Name</label>
                            <form:errors path="prescription.type" cssClass="text-danger"/>
                            <form:input required="required" path="prescription.type.name" type="text"
                                        class="form-control"
                                        id="type-name"
                                        value="${prescriptionInfo.prescription.type.name}"/>
                        </div>
                        <div class="form-group" id="dose-group"
                            ${prescriptionInfo.prescription.type.kind=='Procedure'?'style="display: none;"':''} >
                            <label for="dose" title="'number'mg or 'number'ml">Dose (ml/mg)</label>
                            <form:errors path="prescription.dose" cssClass="text-danger"/>
                            <form:input path="prescription.dose" type="text" class="form-control" id="dose"
                                        value="${prescriptionInfo.prescription.dose}" pattern="^[0-9]+mg|[0-9]+ml|$"/>
                        </div>
                        <div class="form-group">
                            <label>Period</label>
                            <br/>
                            <form:errors path="prescription.startDate" cssClass="text-danger"/>
                            <div class="input-group">
                                <form:input required="required" path="prescription.startDate" type="date"
                                            class="form-control"
                                            value="${prescriptionInfo.prescription.startDate}"/>
                            </div>
                            <form:errors path="prescription.endDate" cssClass="text-danger"/>
                            <div class="input-group">
                                <form:input required="required" path="prescription.endDate" type="date"
                                            class="form-control"
                                            value="${prescriptionInfo.prescription.endDate}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Days</label>
                            <form:errors path="prescription.prescriptionDays" cssClass="text-danger"/>
                            <div class="input-group">
                                <div class="form-check form-check-inline">
                                    <form:checkbox path="prescription.prescriptionDays" value="MONDAY"
                                                   class="form-check-input" id="mon"/>Mon
                                </div>
                                <div class="form-check form-check-inline">
                                    <form:checkbox path="prescription.prescriptionDays" value="TUESDAY"
                                                   class="form-check-input" id="tue"/>Tue
                                </div>
                                <div class="form-check form-check-inline">
                                    <form:checkbox path="prescription.prescriptionDays" value="WEDNESDAY"
                                                   class="form-check-input" id="wed"/>Wed
                                </div>
                                <div class="form-check form-check-inline">
                                    <form:checkbox path="prescription.prescriptionDays" value="THURSDAY"
                                                   class="form-check-input" id="thu"/>Thu
                                </div>
                                <div class="form-check form-check-inline">
                                    <form:checkbox path="prescription.prescriptionDays" value="FRIDAY"
                                                   class="form-check-input" id="fri"/>Fri
                                </div>
                                <div class="form-check form-check-inline">
                                    <form:checkbox path="prescription.prescriptionDays" value="SATURDAY"
                                                   class="form-check-input" id="sat"/>Sat
                                </div>
                                <div class="form-check form-check-inline">
                                    <form:checkbox path="prescription.prescriptionDays" value="SUNDAY"
                                                   class="form-check-input" id="sun"/>Sun
                                </div>
                            </div>
                        </div>
                        <div class="times">
                            <label for="time0">Times:</label>
                            <form:errors path="prescriptionTimes" cssClass="text-danger"/>
                            <c:if test="${not empty prescriptionInfo.prescriptionTimes}">
                                <c:forEach var="time" items="${prescriptionInfo.prescriptionTimes}"
                                           varStatus="loop">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input style="display:none"
                                                   name="prescriptionTimes[${loop.index}].id"
                                                   value="${prescriptionInfo.prescriptionTimes[loop.index].id}">
                                            <input required="required" name="prescriptionTimes[${loop.index}].time"
                                                   type="time"
                                                   class="form-control"
                                                   id="time${loop.index}"
                                                   value="${time.time}"
                                                   step="60"/>
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

    var procAndMedOptions = {
        url: "/RehabilitationClinic/getProceduresAndMedicines",
        list: {
            maxNumberOfElements: 8,
            match: {
                enabled: true
            },
            sort: {
                enabled: true
            }
        },
        listLocation: "${prescriptionInfo.prescription.type.kind}",
        requestDelay: 300
    };
    $("#type-kind").change(function () {
        procAndMedOptions.listLocation = $("#type-kind option:selected").text().trim();
        $("#type-name").easyAutocomplete(procAndMedOptions);
    });
    $("#type-name").easyAutocomplete(procAndMedOptions);
    $('form').submit(function () {
        $('form > div:hidden > input').attr("disabled", true);
        $('form').submit();
    })
</script>

</body>
</html>
