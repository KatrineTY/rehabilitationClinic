<%@tag description="Common page" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="width" required="false" %>

<t:navibar/>
<t:pageTemplate width="${width}">
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:pageTemplate>