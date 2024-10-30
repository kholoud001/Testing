<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Housing Insurance Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/formStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">
</head>
<body>
<%-- NavBar --%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<h1>Housing Insurance Form</h1>

<!-- Spring Form Tag -->
<form:form action="${pageContext.request.contextPath}/housing/submit" method="post" modelAttribute="housing">

    <!-- Insurance Type -->
    <div class="form-group">
        <label for="type">Insurance Type:</label>
        <!-- Display the type as plain text -->
        <p>Housing</p>
        <!-- Hidden input to set the value to "Housing" -->
        <form:input path="type" id="type" type="hidden" value="Housing"/>
    </div>

    <!-- Home Value -->
    <div class="form-group">
        <label for="homeValue">Home Value:</label>
        <form:input path="homeValue" type="number" step="0.01" min="0" id="homeValue" required="true"/>
    </div>

    <!-- Home Type -->
    <div class="form-group">
        <label for="typeHome">Home Type:</label>
        <form:select path="typeHome" id="typeHome" required="true">
            <form:option value="" label="Select Home Type"/>
            <form:option value="Apartment" label="Apartment"/>
            <form:option value="House" label="House"/>
            <form:option value="Condo" label="Condo"/>
            <form:option value="Townhouse" label="Townhouse"/>
        </form:select>
    </div>

    <!-- Location -->
    <div class="form-group">
        <label for="location">Location:</label>
        <form:input path="location" type="text" id="location" placeholder="Enter home location" required="true"/>
    </div>

    <!-- Security System -->
    <div class="form-group">
        <label for="securitySystem">Security System:</label>
        <form:select path="securitySystem" id="securitySystem" required="true">
            <form:option value="" label="Select Security System"/>
            <form:option value="None" label="None"/>
            <form:option value="Basic Alarm" label="Basic Alarm"/>
            <form:option value="Advanced Security" label="Advanced Security"/>
            <form:option value="Smart Home System" label="Smart Home System"/>
        </form:select>
    </div>

    <!-- Submit Button -->
    <div class="form-group">
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form:form>
</body>
</html>
