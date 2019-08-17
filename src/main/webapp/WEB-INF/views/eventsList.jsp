<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="../resources/static/css/bootstrap.min.css">
    <script src="../resources/static/js/jquery-1.11.2.min.js"></script>
    <script src="../resources/static/js/popper.min.js"></script>
    <script src="../resources/static/js/bootstrap.min.js"></script>

    <title>Events</title>
</head>
<body>

<t:page width="10">
    <jsp:body>
        <form action="1">
            <div class="input-group">
                <input class=" form-control" type="text" placeholder="search by patient's name"
                       name="searchPatientName" value="${searchPatientName}"/>
                <input class=" form-control" type="date" placeholder="search by date" name="searchDate"
                       value="${searchDate}"/>
                <input class=" form-control" type="time" placeholder="search by time" name="searchStartTime"
                       value="${searchStartTime}"/>
                <input class=" form-control" type="time" placeholder="search by time" name="searchEndTime"
                       value="${searchEndTime}"/>
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>

        <table id="patientTable" class="table table-hover">
            <thead class="card-header">
            <tr>
                <td><b>Date</b></td>
                <td><b>Patient name</b></td>
                <td><b>Kind of treatment</b></td>
                <td><b>Name of treatment</b></td>
                <td><b>Status</b></td>
                <td><b>Comment</b></td>
                <td><b>Dose</b></td>
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
                        <td>${event.comment}</td>
                        <td>${event.dose}</td>
                        <td>
                            <form action="take-task" method="post">
                                <input style="display:none" name="event.id" type="number" value="${event.id}">
                                <button type="submit" class="btn btn-primary"
                                        <c:if test="${event.status!='Planned'}">disabled</c:if>>Get
                                </button>
                            </form>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#rejectModal" data-event-id="${event.id}"
                                    <c:if test="${event.status!='Planned' && event.status!='In progress'}">disabled</c:if>>
                                Reject
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

        <ul class="pagination justify-content-center">

            <c:if test="${pageCount < 7}">
                <c:forEach var="page" begin="1" end="${pageCount}">
                    <li class="page-item ${currentPage == page ? "active" : ""}">
                        <a class="page-link" href="${page}?${pageContext.request.queryString}">${page}</a>
                    </li>
                </c:forEach>
            </c:if>
            <c:if test="${pageCount > 6}">
                <li class="page-item ${currentPage == 1 ? "disabled" : ""}">
                    <a class="page-link" href="${currentPage - 1}?${pageContext.request.queryString}"
                       tabindex="-1">Previous</a>
                </li>
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        <li class="page-item active">
                            <a class="page-link" href="1?${pageContext.request.queryString}">1</a>
                        </li>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageCount}?${pageContext.request.queryString}">${pageCount}</a>
                        </li>
                    </c:when>
                    <c:when test="${currentPage == 2}">
                        <li class="page-item">
                            <a class="page-link" href="1?${pageContext.request.queryString}">1</a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link" href="2?${pageContext.request.queryString}">2</a>
                        </li>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageCount}?${pageContext.request.queryString}">${pageCount}</a>
                        </li>
                    </c:when>
                    <c:when test="${currentPage == pageCount - 1}">
                        <li class="page-item">
                            <a class="page-link" href="1?${pageContext.request.queryString}">1</a>
                        </li>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link active"
                               href="${pageCount - 1}?${pageContext.request.queryString}">${pageCount - 1}</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageCount}?${pageContext.request.queryString}">${pageCount}</a>
                        </li>
                    </c:when>
                    <c:when test="${currentPage == pageCount}">
                        <li class="page-item">
                            <a class="page-link" href="1?${pageContext.request.queryString}">1</a>
                        </li>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link"
                               href="${pageCount}?${pageContext.request.queryString}">${pageCount}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="1?${pageContext.request.queryString}">1</a>
                        </li>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link"
                               href="${currentPage}?${pageContext.request.queryString}">${currentPage}</a>
                        </li>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageCount}?${pageContext.request.queryString}">${pageCount}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="page-item ${currentPage == pageCount ? "disabled" : ""}">
                    <a class="page-link" href="${currentPage + 1}?${pageContext.request.queryString}">Next</a>
                </li>
            </c:if>
        </ul>
        <div class="modal" id="rejectModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Reject task confirmation</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <form action="<c:url value="/get-events-list/reject-task"/>" method="post" id="commentForm">

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
