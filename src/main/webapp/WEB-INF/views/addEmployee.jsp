<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/static/js/diagnoses.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.easy-autocomplete.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/js/easy-autocomplete.min.css">
    <title>Add employee</title>
</head>
<body>
<t:page>
    <jsp:body>
        <h3 class="card-header">Employee</h3>
        <div class="card-body">
            <div class="row">
                <div class="col-md-8 ml-auto mr-auto">
                    <form:form method="post" modelAttribute="employee" action="add-employee">
                        <div class="fields">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <form:errors path="name" cssClass="text-danger"/>
                                <form:input required="required" path="name" type="text" class="form-control" id="name"
                                            placeholder="name"/>
                            </div>
                            <div class="form-group">
                                <label for="position">Position</label>
                                <form:errors path="position" cssClass="text-danger"/>
                                <form:input required="required" path="position" type="text" class="form-control"
                                            id="position"
                                            placeholder="position"/>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <form:errors path="password" cssClass="text-danger"/>
                                <form:input required="required" path="password" type="password" class="form-control"
                                            id="password"
                                            placeholder="password"/>
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone</label>
                                <form:errors path="phone" cssClass="text-danger"/>
                                <form:input required="required" path="phone" type="text" class="form-control" id="phone"
                                            placeholder="phone"/>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <form:errors path="email" cssClass="text-danger"/>
                                <form:input required="required" path="email" type="text" class="form-control" id="email"
                                            placeholder="email"/>
                            </div>
                            <div class="form-group">
                                <label for="role">Role</label>
                                <form:select id="role" path="role.id" class="form-control">
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label for="workingTime">Working time</label>
                                <form:select id="workingTime" path="workingTime.id" class="form-control">
                                    <c:forEach items="${workingTimes}" var="workingTime">
                                        <option value="${workingTime.id}">${workingTime.startHours}-${workingTime.endHours}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Add employee</button>
                    </form:form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:page>
<script>
    var options = {
        url: "/RehabilitationClinic/getDoctors",
        getValue: function (element) {
            return element;
        },
        template: {
            type: "description",
            fields: {
                description: "realName"
            }
        },
        list: {
            maxNumberOfElements: 8,
            match: {
                enabled: true
            },
            sort: {
                enabled: true
            }
        },
    };

    $("#attendingDoctor").easyAutocomplete(options);
</script>
</body>
</html>
