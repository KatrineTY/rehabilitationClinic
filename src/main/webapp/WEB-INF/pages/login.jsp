<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <h3 class="card-header">Sign In</h3>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-8 ml-auto mr-auto">
                            <form:form method="post" modelAttribute="employee" action="check-user">
                                <div class="form-group">
                                    <form:label path="login" for="login">Login</form:label>
                                    <form:input path="login" class="form-control" id="login"
                                                placeholder="login"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="password" for="password">Password</form:label>
                                    <form:input path="password" type="password" class="form-control" id="password"
                                                placeholder="password"/>
                                </div>
                                <%--                                This functionality is not working now--%>
                                <div class="form-group form-check">
                                    <input type="checkbox" class="form-check-input" id="remember">
                                    <label class="form-check-label" for="remember">Remember me</label>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
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
