<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/static/js/diagnoses.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery-1.11.2.min.js"></script>

    <title>Edit patient</title>
</head>
<body>
<t:page>
    <jsp:body>
        <h3 class="card-header">Patient</h3>
        <div class="card-body">
            <div class="row">
                <div class="col-md-8 ml-auto mr-auto">
                    <form:form method="post" modelAttribute="patientInfo" action="${patientInfo.patient.id}">
                        <div class="fields">
                            <input style="display:none" name="patient.id" value="${patientInfo.patient.id}"
                                   type="number">
                            <input style="display:none" name="patientCard.id"
                                   value="${patientInfo.patientCard.id}" type="number">
                            <input style="display:none" name="patient.insurance"
                                   value="${patientInfo.patient.insurance}" type="text">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <form:errors path="patient.name" cssClass="text-danger"/>
                                <form:input required="required" path="patient.name" type="text" class="form-control"
                                            id="name"
                                            value="${patientInfo.patient.name}"/>
                            </div>
                            <div class="form-group">
                                <label for="attendingDoctor">Attending Doctor</label>
                                <form:select id="attendingDoctor" path="patientCard.attendingDoctor.name"
                                             class="form-control">
                                    <c:forEach var="doctor" items="${doctors}">
                                        <option value="${doctor}"
                                            ${doctor==patientInfo.patientCard.attendingDoctor.name?'selected':'none'}>
                                                ${doctor}
                                        </option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label for="building">Building</label>
                                <form:select id="building" path="patientCard.building" class="form-control">
                                    <c:forTokens items="A,B,C" delims="," var="corpus">
                                        <option value="${corpus}"
                                            ${corpus==patientInfo.patientCard.building?'selected':'none'}>${corpus}</option>
                                    </c:forTokens>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label for="ward">Ward</label>
                                <form:select id="ward" path="patientCard.ward" class="form-control">
                                    <c:forEach var="room" begin="1" end="9">
                                        <option value="${room}"
                                            ${room==patientInfo.patientCard.ward?'selected':'none'}>${room}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label for="status">Status</label>
                                <form:select path="patientCard.status" class="form-control" id="status">
                                    <option value="Treated"
                                        ${'Treated'==patientInfo.patientCard.status?'selected':'none'}>Treated
                                    </option>
                                    <option value="Discharged"
                                        ${'Discharged'==patientInfo.patientCard.status?'selected':'none'}>
                                        Discharged
                                    </option>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label for="diagnosis0">Diagnoses:</label>
                                <form:errors path="diagnoses" cssClass="text-danger"/>
                                <c:if test="${not empty patientInfo.diagnoses}">
                                    <c:forEach var="diagnosis" items="${patientInfo.diagnoses}"
                                               varStatus="loop">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input style="display:none" name="diagnoses[${loop.index}].id"
                                                       value="${patientInfo.diagnoses[loop.index].id}">
                                                <input style="display:none" name="diagnoses[${loop.index}].startDate"
                                                       value="${patientInfo.diagnoses[loop.index].startDate}"
                                                       type="datetime-local">
                                                <input style="display:none" name="diagnoses[${loop.index}].endDate"
                                                       value="${patientInfo.diagnoses[loop.index].endDate}"
                                                       type="datetime-local">
                                                <div class="input-group">
                                                    <input required="required" name="diagnoses[${loop.index}].name"
                                                           type="text"
                                                           class="form-control"
                                                           id="diagnosis${loop.index}"
                                                           value="${diagnosis.name}"/>
                                                    <div class="input-group-append">
                                                        <button class="btn btn-primary" type="button"
                                                                onclick="addDiagnosis()">
                                                            +
                                                        </button>
                                                    </div>
                                                    <c:if test="${loop.index != 0}">
                                                        <button class="btn btn-danger" type="button"
                                                                onclick="removeDiagnosis(this)">
                                                            ×
                                                        </button>
                                                    </c:if>
                                                </div>
                                                <textarea name="diagnoses[${loop.index}].comment" class="form-control"
                                                          rows="0.5">${diagnosis.comment}</textarea>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${empty patientInfo.diagnoses}">
                                    <div class="input-group">
                                        <input required="required" name="diagnoses[0].name" type="text"
                                               class="form-control"
                                               id="diagnosis0"
                                               placeholder="diagnosis"/>
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button"
                                                    onclick="addDiagnosis()">
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
                </div>
            </div>
        </div>
    </jsp:body>
</t:page>
</body>
</html>
