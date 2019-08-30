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
    <script src="${pageContext.request.contextPath}/resources/static/js/bootstrap.min.js"></script>

    <title>Events</title>
</head>
<body>

<t:page width="14">
    <jsp:body>
        <table id="patientTable" class="table table-hover">
            <thead class="card-header">
            <tr>
                <td><b>Date</b></td>
                <td><b>Patient name</b></td>
                <td><b>Kind of treatment</b></td>
                <td><b>Name of treatment</b></td>
                <td><b>Building</b></td>
                <td><b>Ward</b></td>
                <td><b>Status</b></td>
                <td><b>Dose</b></td>
                <td>Finish task</td>
                <td>Reject task</td>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty events}">
                <c:forEach var="event" items="${events}">
                    <tr>
                        <td><javatime:format value="${event.date}" style="MS"/></td>
                        <td>${event.patient.name}</td>
                        <td>${event.type.kind}</td>
                        <td>${event.type.name}</td>
                        <td>${event.building}</td>
                        <td>${event.ward}</td>
                        <td>${event.status}</td>
                        <td>${event.dose}</td>
                        <td>
                            <form action="taken-events/finish-task" method="post">
                                <input style="display:none" name="event.id" type="number" value="${event.id}">
                                <button type="submit" class="btn btn-primary"> Finish</button>
                            </form>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#rejectModal" data-event-id="${event.id}"> Reject
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

        <div class="modal" id="rejectModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Reject task confirmation</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <form action="<c:url value="/taken-events/reject-task"/>" method="post" id="commentForm">

                        <div class="modal-body">
                            <input style="display:none" name="event.id" type="number">
                            <input name="comment" placeholder="Please, enter your reason" type="text"
                                   required="required" title="This field is required"
                                   pattern="[a-zA-Z0-1][a-zA-Z0-1 ]+">
                        </div>
                    </form>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" form="commentForm">Confirm</button>
                    </div>

                </div>
            </div>
        </div>
    </jsp:body>
</t:page>
<script>
    $('#rejectModal').on('show.bs.modal', function (e) {
        let eventId = $(e.relatedTarget).data('event-id');
        $(e.currentTarget).find('input[name="event.id"]').val(eventId);
    });
</script>
</body>
</html>
