<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Generate Contract for Automobile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/formStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">
</head>
<body>
<%-- NavBar inclusion --%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<h1>Generate Contract for Your Insurance</h1>



<form:form action="${pageContext.request.contextPath}/contract/submit" method="post" modelAttribute="contract" enctype="multipart/form-data">
    <!-- Include a hidden field for insuranceId -->
    <input type="hidden" name="id" value="${insuranceId}" />
    <input type="hidden" name="insuranceType" value="${insuranceType}" />

    <div class="form-group">
        <label for="insuranceType">Insurance Type:</label>
        <span id="insuranceType">${insuranceType}</span>
    </div>

    <!-- Document Upload -->
    <div class="form-group">
        <label for="document">Upload Contract Document:</label>
        <input type="file" id="document" name="document" required/>
    </div>

    <!-- Submit Button -->
    <div class="form-group">
        <button type="submit" class="btn btn-primary">Generate Contract</button>
    </div>
</form:form>

</body>
</html>
