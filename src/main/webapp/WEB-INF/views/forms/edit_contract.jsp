<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Contract</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/formStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">
</head>
<body>
<%-- NavBar inclusion --%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<h1>Edit Contract</h1>

<form:form action="${pageContext.request.contextPath}/contract/update" method="post" modelAttribute="contract" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${contract.id}" />
    <input type="hidden" name="insuranceType" value="${insuranceType}" />
    <input type="hidden" name="insuranceId" value="${insuranceId}" />


    <div class="form-group">
        <label for="insuranceType">Insurance Type:</label>
        <span id="insuranceType">${insuranceType}</span>
    </div>

    <div class="form-group">
        <label for="document">Upload New Contract Document:</label>
        <input type="file" id="document" name="document" required />
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary">Update Contract</button>
    </div>
</form:form>

</body>
</html>
