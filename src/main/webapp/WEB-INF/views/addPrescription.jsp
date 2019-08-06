<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/static/css/pageTemplate.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./resources/static/js/prescriptions.js"></script>
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
                            <form:form method="post" modelAttribute="prescriptionInfo" action="add-prescription">
                                <div class="form-group">
                                    <label for="name">Patient name</label>
                                    <form:errors path="prescription.patient.name" cssClass="text-danger"/>
                                    <input name="prescription.patient.name" type="text" class="form-control" id="name"
                                           placeholder="name"/>
                                </div>
                                <div class="form-group">
                                    <label for="type.name">Procedure/Medicament</label>
                                    <select name="prescription.type.kind" class="form-control" id="type.name">
                                        <option value="Procedure" onclick="hideDose()">Procedure</option>
                                        <option value="Medicament" selected onclick="showDose()">Medicament</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="type.kind">Name</label>
                                    <input name="prescription.type.name" type="text" class="form-control" id="type.kind"
                                           placeholder="name"/>
                                </div>
                                <div class="form-group" id="dose-group">
                                    <label for="dose">Dose</label>
                                    <form:errors path="prescription.dose" cssClass="text-danger"/>
                                    <input name="prescription.dose" type="text" class="form-control" id="dose"
                                           placeholder="dose"/>
                                </div>
                                <div class="form-group">
                                    <label>Period</label>
                                    <div class="input-group">
                                        <form:errors path="prescription.startDate" cssClass="text-danger"/>
                                        <input name="prescription.startDate" type="date" class="form-control">
                                        <form:errors path="prescription.endDate" cssClass="text-danger"/>
                                        <input name="prescription.endDate" type="date" class="form-control">
                                    </div>
                                </div>
                                <div class="times">
                                    <div class="form-group">
                                        <label for="prescriptionTimes0">Time:</label>
                                        <form:errors path="prescriptionTimes[0].time" cssClass="text-danger"/>
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


                                <button type="submit" class="btn btn-primary">Add prescription</button>
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
