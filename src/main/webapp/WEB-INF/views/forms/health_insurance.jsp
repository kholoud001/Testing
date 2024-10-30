<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Health Insurance Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/formStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">
</head>
<body>
<%-- NavBar --%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<h1>Health Insurance Form</h1>

<!-- Spring Form Tag -->
<form:form action="${pageContext.request.contextPath}/health/submit" method="post" modelAttribute="health">

    <!-- Insurance Type -->
    <div class="form-group">
        <label for="type">Insurance Type:</label>
        <!-- Display the type as plain text -->
        <p>Health</p>
        <!-- Hidden input to set the value to "Health" -->
        <form:input path="type" id="type" type="hidden" value="Health"/>
    </div>
    

    <!-- Age -->
    <div class="form-group">
        <label for="age">Age:</label>
        <form:input path="age" type="number" min="0" id="age" required="true"/>
    </div>

    <!-- Health State -->
    <div class="form-group">
        <label for="healthState">Health State:</label>
        <form:input path="healthState" type="text" id="healthState" placeholder="Enter health state" required="true"/>
    </div>

    <!-- Medical Coverage Type -->
    <div class="form-group">
        <label for="medicalCoverageType">Medical Coverage Type:</label>
        <form:select path="medicalCoverageType" id="medicalCoverageType" required="true">
            <form:option value="" label="Select Coverage Type"/>
            <form:option value="Basic" label="Basic"/>
            <form:option value="Premium" label="Premium"/>
        </form:select>
    </div>

    <!-- Submit Button -->
    <div class="form-group">
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form:form>
</body>
</html>
