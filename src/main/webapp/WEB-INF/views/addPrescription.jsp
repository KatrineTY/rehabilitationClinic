<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <link rel="stylesheet" href="./resources/static/css/bootstrap.min.css">
    <script src="./resources/static/js/prescriptions.js"></script>
    <script src="./resources/static/js/jquery-1.11.2.min.js"></script>
    <script src="./resources/static/js/jquery.easy-autocomplete.min.js"></script>
    <script src="./resources/static/js/doseHide.js"></script>
    <link rel="stylesheet" href="./resources/static/js/easy-autocomplete.min.css">

    <title>Add prescription</title>
</head>
<body>
<t:page>
    <jsp:body>
        <h3 class="card-header">Prescription</h3>
        <div class="card-body">
            <div class="row">
                <div class="col-md-8 ml-auto mr-auto">
                    <form:form method="post" modelAttribute="prescriptionInfo" action="add-prescription">
                        <div class="form-group">
                            <label for="name">Patient name</label>
                            <form:errors path="prescription.patient.name" cssClass="text-danger"/>
                            <form:input path="prescription.patient.name" type="text" class="form-control"
                                        id="name"
                                        placeholder="name"/>
                        </div>
                        <div class="form-group">
                            <label for="type-kind">Procedure/Medicament</label>
                            <form:errors path="prescription.type.kind" cssClass="text-danger"/>
                            <select name="prescription.type.kind" class="form-control" id="type-kind">
                                <option value="Procedure" onclick="hideDose()">Procedure</option>
                                <option value="Medicament" selected onclick="showDose()">Medicament</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="type-name">Name</label>
                            <form:errors path="prescription.type" cssClass="text-danger"/>
                            <form:input path="prescription.type.name" type="text" class="form-control"
                                        id="type-name"
                                        placeholder="name"/>
                        </div>
                        <div class="form-group" id="dose-group">
                            <label for="dose">Dose</label>
                            <form:errors path="prescription.dose" cssClass="text-danger"/>
                            <form:input path="prescription.dose" type="text" class="form-control" id="dose"
                                        placeholder="dose"/>
                        </div>
                        <div class="form-group">
                            <label>Period</label>
                            <div class="input-group">
                                <form:errors path="prescription.startDate" cssClass="text-danger"/>
                                <form:input path="prescription.startDate" type="date" class="form-control"/>
                                <form:errors path="prescription.endDate" cssClass="text-danger"/>
                                <form:input path="prescription.endDate" type="date" class="form-control"/>
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
                            <div class="form-group">
                                <label for="prescriptionTimes0">Time:</label>
                                <form:errors path="prescriptionTimes" cssClass="text-danger"/>
                                <div class="input-group">
                                    <input name="prescriptionTimes[0].time" type="time" class="form-control"
                                           id="prescriptionTimes0"/>
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button"
                                                onclick="addPrescriptionTime()">
                                            +
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary" id="submit">Add prescription</button>
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
        listLocation: "Medicament",
        requestDelay: 300
    };
    $("#type-name").easyAutocomplete(procAndMedOptions);
    $("#type-kind").change(function () {
        procAndMedOptions.listLocation = $("#type-kind option:selected").text().trim();
        $("#type-name").easyAutocomplete(procAndMedOptions);
    });
    $('form').submit(function () {
        $('form > div:hidden > input').attr("disabled", true);
        $('form').submit();
    })
</script>
</body>
</html>
