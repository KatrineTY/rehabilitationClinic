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
                <h3 class="card-header">${employee.name}</h3>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12 ml-auto mr-auto">
                            <a href="addPatient" class="btn btn-primary">Add patient</a>
                        </div>
                        <div class="col-md-12 ml-auto mr-auto mt-2">
                            <a href="searchPatient" class="btn btn-primary">Search patient</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
