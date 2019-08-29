<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/static/js/search.js"></script>

    <title>Employees</title>
</head>
<body>
<t:page>
    <jsp:body>
        <input class=" form-control" type="text" placeholder="Search"
               aria-label="Search" onkeyup="search()" id="searchByName">
        <table class="table table-hover">
            <thead class="card-header">
            <tr>
                <td><b>Name</b></td>
                <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                    <td>Edit</td>
                    <td>Disable</td>
                </security:authorize>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty employees}">
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>${employee.name}</td>
                        <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                            <td><a href="edit-employee/${employee.id}">Edit</a></td>
                            <td>
                                <form action="disable-employee" method="post">
                                    <input style="display: none" type="number" value="${employee.id}"
                                           name="id"/>
                                    <button type="submit" class="btn btn-primary"
                                        ${employee.enabled=='false'?'disabled':'none'}>Disable
                                    </button>
                                </form>
                            </td>
                        </security:authorize>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </jsp:body>
</t:page>

</body>
</html>
