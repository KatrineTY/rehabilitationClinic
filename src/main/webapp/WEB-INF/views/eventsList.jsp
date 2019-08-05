<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 ml-auto mr-auto">
            <div class="card bg-custom mb-5">
                <table id="patientTable" class="table table-hover">
                    <thead class="card-header">
                    <tr>
                        <td><b>Date</b></td>
                        <td><b>Patient name</b></td>
                        <td><b>Kind of treatment</b></td>
                        <td><b>Name of treatment</b></td>
                        <td><b>Status</b></td>
                        <td>Get task</td>
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
                                <td>${event.status}</td>
                                <td>
                                    <form action="take-task/${event.id}" method="post">
                                        <button type="submit" class="btn btn-primary">Get</button>
                                    </form>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#rejectModal" data-event-id="${event.id}">Reject
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
                            <form action="reject-task" method="post" id="commentForm">

                                <div class="modal-body">
                                    <input style="display:none" name="event.id" type="number">
                                    <input name="comment" placeholder="Please, enter your reason" type="text">
                                </div>
                            </form>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary" form="commentForm">Confirm</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('#rejectModal').on('show.bs.modal', function (e) {
        var eventId = $(e.relatedTarget).data('event-id');
        $(e.currentTarget).find('input[name="event.id"]').val(eventId);
    });
</script>

</body>
</html>
