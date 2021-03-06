<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <title>Error</title>
</head>
<t:loading/>
<t:pageTemplate>
    <jsp:body>
        <div class="card-body">
            <h2 class="text-center">Sorry, but there is something wrong with the additional services.
                Please, try later or call administrator</h2>
            <a class="btn btn-primary" href="${pageContext.request.getHeader("referer")}">Back</a>
        </div>
    </jsp:body>
</t:pageTemplate>
</html>
