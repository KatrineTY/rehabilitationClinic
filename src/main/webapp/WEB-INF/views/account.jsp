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
                <h3 class="card-header">${username}</h3>
                <div class="card-body">
                    <ul class="list-group">
                        <a href="new-patient" class="list-group-item list-group-item-action">Add patient</a>
                        <a href="search-patient" class="list-group-item list-group-item-action">Search patient</a>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

