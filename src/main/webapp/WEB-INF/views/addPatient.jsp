<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/static/css/pageTemplate.css">
    <script src="./resources/static/js/diagnoses.js"></script>
    <script src="./resources/static/js/jquery-1.11.2.min.js"></script>
    <script src="./resources/static/js/jquery.easy-autocomplete.min.js"></script>
    <link rel="stylesheet" href="./resources/static/js/easy-autocomplete.min.css">
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
                                <input name="patientCard.attendingDoctor.name" type="text"
                                       id="attendingDoctor"
                                       style="display: none"
                                       value="${sessionScope.empName}"/>
                                <div class="fields">
                                    <div class="form-group">
                                        <label for="name">Name</label>
                                        <form:errors path="patient.name" cssClass="text-danger"/>
                                        <form:input path="patient.name" type="text" class="form-control" id="name"
                                                    placeholder="name"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="insurance">Insurance</label>
                                        <form:errors path="patient.insurance" cssClass="text-danger"/>
                                        <form:input path="patient.insurance" type="text" class="form-control"
                                                    id="insurance"
                                                    placeholder="insurance"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="building">Building</label>
                                        <form:select id="building" path="patientCard.building" class="form-control">
                                            <c:forTokens items="A,B,C" delims="," var="corpus">
                                                <option value="${corpus}">${corpus}</option>
                                            </c:forTokens>
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="ward">Ward</label>
                                        <form:errors path="patientCard.ward" cssClass="text-danger"/>
                                        <form:select id="ward" path="patientCard.ward" class="form-control">
                                            <c:forEach var="room" begin="1" end="9">
                                                <option value="${room}">${room}</option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="diagnosis0">Diagnoses:</label>
                                        <form:errors path="diagnoses" cssClass="text-danger"/>
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
<script>

    var options = {

        url: "/RehabilitationClinic/getDoctors",

        getValue: function (element) {
            return element;
        },

        template: {
            type: "description",
            fields: {
                description: "realName"
            }
        },

        list: {
            maxNumberOfElements: 8,
            match: {
                enabled: true
            },
            sort: {
                enabled: true
            }
        },

    };

    $("#attendingDoctor").easyAutocomplete(options);
</script>
</body>
</html>
