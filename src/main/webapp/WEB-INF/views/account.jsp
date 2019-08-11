<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/static/css/pageTemplate.css">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 ml-auto mr-auto">
            <div class="card bg-custom mb-5">
                <h3 class="card-header">${empName}</h3>
                <div class="card-body">
                    <ul class="list-group">
                        <a href="add-patient" class="list-group-item list-group-item-action">Add patient</a>
                        <a href="search-patient" class="list-group-item list-group-item-action">Search patient</a>
                        <a href="add-prescription" class="list-group-item list-group-item-action">Add prescription</a>
                        <a href="get-prescriptions-list" class="list-group-item list-group-item-action">Prescriptions
                            list</a>
                        <a href="get-event-list/1" class="list-group-item list-group-item-action">Events list</a>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

