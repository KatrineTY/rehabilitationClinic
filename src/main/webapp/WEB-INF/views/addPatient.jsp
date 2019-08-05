<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/static/css/pageTemplate.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="./resources/static/js/diagnoses.js"></script>
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
                            <form:form method="post" modelAttribute="patientInfo" action="add-patient">
                                <div class="fields">
                                    <div class="form-group">
                                        <label for="name">Name</label>
                                        <input name="patient.name" type="text" class="form-control" id="name"
                                               placeholder="name"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="insurance">Insurance</label>
                                        <input name="patient.insurance" type="number" class="form-control"
                                               id="insurance"
                                               placeholder="insurance"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="attendingDoctor">Attending Doctor</label>
                                        <input name="patientCard.attendingDoctor.name" type="text"
                                               class="form-control"
                                               id="attendingDoctor"
                                               placeholder="attending doctor"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="building">Building</label>
                                        <input name="patientCard.building" type="text" class="form-control"
                                               id="building"
                                               placeholder="building"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="ward">Ward</label>
                                        <input name="patientCard.ward" type="number" class="form-control" id="ward"
                                               placeholder="ward"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="diagnosis0">Diagnoses:</label>
                                        <div class="input-group">
                                            <input name="diagnoses[0].name" type="text" class="form-control"
                                                   id="diagnosis0"
                                                   placeholder="diagnosis"/>
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button" onclick="addDiagnosis()">
                                                    +
                                                </button>
                                            </div>
                                        </div>
                                        <textarea name="diagnoses[0].comment" class="form-control"
                                                  rows="0.5" placeholder="comment"></textarea>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">Add patient</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
