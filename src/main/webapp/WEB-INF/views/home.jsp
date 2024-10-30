<%--
  Created by IntelliJ IDEA.
  User: youcode
  Date: 10/22/2024
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/homeStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">

    <title>HomePage</title>
</head>
<body>
<%--NavBar--%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<%--cards--%>
<div class="cards content">
<div class="d-flex justify-content-center container mt-5">
    <a href="${pageContext.request.contextPath}/automobile/form">
        <div class="card p-3 bg-white"><i class="fa fa-apple"></i>
        <div class="about-product"><img src="${pageContext.request.contextPath}/resources/images/auto.png">
            <div>
                <h4>Automobile</h4>
                <h5 class="mt-0 text-black-50">Insurance Quote</h5>
            </div>
        </div>
    </div>
    </a>
</div>
<div class="d-flex justify-content-center container mt-5">
    <a href="${pageContext.request.contextPath}/housing/form">
        <div class="card p-3 bg-white"><i class="fa fa-apple"></i>
        <div class="about-product"><img src="${pageContext.request.contextPath}/resources/images/home1.png">
            <div>
                <h4>Housing</h4>
                <h5 class="mt-0 text-black-50">Insurance Quote</h5>
            </div>
        </div>
    </div>
    </a>
</div>
<div class="d-flex justify-content-center container mt-5">
    <a href="${pageContext.request.contextPath}/health/form">
        <div class="card p-3 bg-white"><i class="fa fa-apple"></i>
        <div class="about-product"><img src="${pageContext.request.contextPath}/resources/images/health.png">
            <div>
                <h4>Health</h4>
                <h5 class="mt-0 text-black-50">Insurance Quote</h5>
            </div>
        </div>
    </div>
    </a>
</div>
</div>

</body>
</html>
