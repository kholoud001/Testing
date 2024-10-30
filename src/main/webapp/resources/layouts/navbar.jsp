<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="navbar">
    <div class="left">
        <a class="active" href="${pageContext.request.contextPath}/home">Home</a>
    </div>

    <div class="right">
        <a href="javascript:void(0)">Contact</a>

        <c:if test="${not empty sessionScope.loggedInUser}">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
            <div class="dropdown">
                <a href="javascript:void(0)" class="dropbtn">My Insurance Quotes</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/automobile/my-insurances">Auto Insurance</a>
                    <a href="${pageContext.request.contextPath}/housing/my-insurances">Housing Insurance</a>
                    <a href="${pageContext.request.contextPath}/health/my-insurances">Health Insurance</a>

                </div>
            </div>
        </c:if>

        <c:if test="${empty sessionScope.loggedInUser}">
            <a href="${pageContext.request.contextPath}/login">Login</a>
            <a href="${pageContext.request.contextPath}/register">Register</a>
        </c:if>
    </div>
</div>
