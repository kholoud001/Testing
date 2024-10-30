<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Health Insurances</title>
    <!-- Linking to external CSS files for consistent styling -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/tableStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">
</head>
<body>
<%-- NavBar inclusion --%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<h1>Your Health Insurances</h1>

<c:choose>
    <c:when test="${not empty healthInsurances}">
        <div class="table-container">
            <table class="styled-table">
                <thead>
                <tr>
                    <th>age</th>
                    <th>healthState</th>
                    <th>medicalCoverageType</th>
                    <th>Price (MAD)</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="insurance" items="${healthInsurances}">
                    <tr>
                        <td><c:out value="${insurance.age}"/></td>
                        <td><c:out value="${insurance.healthState}"/></td>
                        <td><c:out value="${insurance.medicalCoverageType}"/></td>
                        <td><c:out value="${insurance.price}"/> MAD</td>
                        <td>
                            <!-- Delete action button -->
                            <form action="${pageContext.request.contextPath}/automobile/delete" method="post" style="display:inline;">
                                <input type="hidden" name="insuranceId" value="${insurance.id}" />
                                <button type="submit" class="btn-delete">Delete</button>
                            </form>

                            <!-- Generate Contract action button -->
                            <form action="${pageContext.request.contextPath}/contract/health" method="get" style="display:inline;">
                                <input type="hidden" name="insuranceId" value="${insurance.id}" />
                                <input type="hidden" name="insuranceType" value="Health" />
                                <button type="submit" class="btn-generate">Generate Contract</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <p class="no-insurance-message">No Health insurances found for the logged-in user.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
