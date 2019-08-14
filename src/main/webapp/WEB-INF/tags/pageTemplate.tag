<%@tag description="Page template" pageEncoding="UTF-8" %>
<%@attribute name="width" required="false" %>

<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-${not empty width?width:6} ml-auto mr-auto">
            <div class="card bg-custom mb-5">
                <jsp:doBody/>
            </div>
        </div>
    </div>
</div>
</body>
