<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/static/js/validation.js" type="javascript"></script>
    <title>Login</title>
</head>
<body>
<t:loading/>
<t:pageTemplate>
    <h3 class="card-header">Sign In</h3>
    <div class="card-body">
        <div class="row">
            <div class="col-md-8 ml-auto mr-auto">
                <form method="post" action="j_spring_security_check" id="loginForm">
                    <c:if test="${not empty error}">
                        <div style="color:red; font-weight: bold; margin: 30px 0px;">${error}</div>
                    </c:if>
                    <div class="form-group">
                        <label for="login">Login</label>
                        <input name="user_login" type="text" class="form-control" id="login"
                               placeholder="login"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input name="password_login" type="password" class="form-control" id="password"
                               placeholder="password"/>
                    </div>
                    <div class="form-group form-check">
                        <input name="_spring_security_remember_me" type="checkbox" class="form-check-input"
                               id="remember">
                        <label class="form-check-label" for="remember">Remember me</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</t:pageTemplate>
</body>
</html>
