<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <h3 class="card-header">Patient</h3>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-8 ml-auto mr-auto">
                            <form:form method="post" modelAttribute="patient" action="add-patient">
                                <div class="form-group">
                                    <form:label path="name" for="name">Name</form:label>
                                    <form:input path="name" type="text" class="form-control" id="name"
                                                placeholder="name"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="diagnosis" for="diagnosis">Diagnosis</form:label>
                                    <form:input path="diagnosis" type="text" class="form-control" id="diagnosis"
                                                placeholder="diagnosis"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="insurance" for="insurance">Insurance</form:label>
                                    <form:input path="insurance" type="text" class="form-control" id="insurance"
                                                placeholder="insurance"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="status" for="status">Status</form:label>
                                    <form:input path="status" type="text" class="form-control" id="status"
                                                placeholder="status"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="attendingDoctor.name"
                                                for="attendingDoctor">Attending Doctor</form:label>
                                    <form:input path="attendingDoctor.name" type="text" class="form-control"
                                                id="attendingDoctor"
                                                placeholder="attending doctor"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="building" for="building">Building</form:label>
                                    <form:input path="building" type="text" class="form-control" id="building"
                                                placeholder="building"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="ward" for="ward">Ward</form:label>
                                    <form:input path="ward" type="text" class="form-control" id="ward"
                                                placeholder="ward"/>
                                </div>

                                <button type="submit" class="btn btn-primary">Add patient</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
