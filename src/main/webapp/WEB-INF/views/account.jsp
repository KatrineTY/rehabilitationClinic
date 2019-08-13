<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./resources/static/css/bootstrap.min.css">
    <script>
        window.addEventListener('load', () => {
            document.getElementById('loading').style.display = 'none';
        });
    </script>

    <title>Account</title>
</head>
<body>
<div id="loading" style="text-align: center">
    <span>Loading...</span>
</div>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 ml-auto mr-auto">
            <div class="card bg-custom mb-5">
                <h3 class="card-header">${empName}</h3>
                <div class="card-body">
                    <div class="list-group">
                        <a href="add-patient" class="list-group-item list-group-item-action">Add patient</a>
                        <a href="search-patient" class="list-group-item list-group-item-action">Patients list</a>
                        <a href="add-prescription" class="list-group-item list-group-item-action">Add prescription</a>
                        <a href="get-prescriptions-list" class="list-group-item list-group-item-action">Prescriptions
                            list</a>
                        <a href="get-events-list/1" class="list-group-item list-group-item-action">Events list</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

