<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="../resources/static/js/diagnoses.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<form:form method="post" modelAttribute="patientInfo" action="../edit-patient">
    <div class="fields">
        <input style="display:none" name="patient.id" value="${patientInfo.patient.id}">
        <input style="display:none" name="patientCard.id" value="${patientInfo.patientCard.id}">
        <div class="form-group">
            <label for="name">Name</label>
            <input name="patient.name" type="text" class="form-control" id="name"
                   value="${patientInfo.patient.name}"/>
        </div>
        <div class="form-group">
            <label for="insurance">Insurance</label>
            <input name="patient.insurance" type="text" class="form-control"
                   id="insurance"
                   value="${patientInfo.patient.insurance}"/>
        </div>
        <div class="form-group">
            <label for="attendingDoctor">Attending Doctor</label>
            <input name="patientCard.attendingDoctor.name" type="text"
                   class="form-control"
                   id="attendingDoctor"
                   value="${patientInfo.patientCard.attendingDoctor.name}"/>
        </div>
        <div class="form-group">
            <label for="building">Building</label>
            <input name="patientCard.building" type="text" class="form-control"
                   id="building"
                   value="${patientInfo.patientCard.building}"/>
        </div>
        <div class="form-group">
            <label for="ward">Ward</label>
            <input name="patientCard.ward" type="text" class="form-control" id="ward"
                   value="${patientInfo.patientCard.ward}"/>
        </div>
        <div class="form-group">
            <label for="status">Status</label>
            <input name="patientCard.status" type="text" class="form-control" id="status"
                   value="${patientInfo.patientCard.status}"/>
        </div>
        <div class="form-group">
            <label for="diagnosis0">Diagnoses:</label>
            <c:if test="${not empty patientInfo.diagnoses}">
                <c:forEach var="diagnosis" items="${patientInfo.diagnoses}" varStatus="loop">
                    <input style="display:none" name="diagnoses[${loop.index}].id" value="${patientInfo.diagnoses[loop.index].id}">
                    <input style="display:none" name="diagnoses[${loop.index}].startDate" value="${patientInfo.diagnoses[loop.index].startDate}" type="datetime-local">
                    <input style="display:none" name="diagnoses[${loop.index}].endDate" value="${patientInfo.diagnoses[loop.index].endDate}" type="datetime-local">
                    <div class="input-group">
                        <input name="diagnoses[${loop.index}].name" type="text" class="form-control"
                               id="diagnosis${loop.index}"
                               value="${diagnosis.name}"/>
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button" onclick="addDiagnosis()">
                                +
                            </button>
                        </div>
                    </div>
                    <textarea name="diagnoses[${loop.index}].comment" class="form-control"
                              rows="0.5" placeholder="${diagnosis.comment}"></textarea>
                </c:forEach>
            </c:if>

            <c:if test="${empty patientInfo.diagnoses}">
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
            </c:if>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Save patient</button>
</form:form>
</body>
</html>
