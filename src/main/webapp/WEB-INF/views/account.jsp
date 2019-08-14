<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<head>
    <link rel="stylesheet" href="./resources/static/css/bootstrap.min.css">
    <title>Account</title>
</head>
<t:loading/>
<t:page>
    <jsp:body>
        <h3 class="card-header">${empName}</h3>
        <div class="card-body">
            <div class="list-group">
                <a href="add-patient" class="list-group-item list-group-item-action">Add patient</a>
                <a href="add-prescription" class="list-group-item list-group-item-action">Add prescription</a>
                <a href="patients-list" class="list-group-item list-group-item-action">Patients list</a>
                <a href="get-prescriptions-list" class="list-group-item list-group-item-action">Prescriptions
                    list</a>
                <a href="get-events-list/1" class="list-group-item list-group-item-action">Events list</a>
            </div>
        </div>
    </jsp:body>
</t:page>

