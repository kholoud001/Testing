<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Housing Insurances</title>
    <!-- Linking to external CSS files for consistent styling -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/tableStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">
</head>
<body>
<%-- NavBar inclusion --%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<h1>Your Housing Insurances</h1>

<c:choose>
    <c:when test="${not empty housingInsurances}">
        <div class="table-container">
            <table class="styled-table">
                <thead>
                <tr>
                    <th>Value</th>
                    <th>Type</th>
                    <th>Location</th>
                    <th>Security</th>
                    <th>Price (MAD)</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="insurance" items="${housingInsurances}">
                    <tr>
                        <td><c:out value="${insurance.homeValue}"/></td>
                        <td><c:out value="${insurance.typeHome}"/></td>
                        <td><c:out value="${insurance.location}"/></td>
                        <td><c:out value="${insurance.securitySystem}"/></td>
                        <td><c:out value="${insurance.price}"/> MAD</td>
                        <td>
                            <!-- Delete action button -->
                            <form action="${pageContext.request.contextPath}/automobile/delete" method="post" style="display:inline;">
                                <input type="hidden" name="insuranceId" value="${insurance.id}" />
                                <button type="submit" class="btn-delete">Delete</button>
                            </form>

                            <!-- Generate Contract action button -->
                            <form action="${pageContext.request.contextPath}/contract/housing" method="get" style="display:inline;">
                                <input type="hidden" name="insuranceId" value="${insurance.id}" />
                                <input type="hidden" name="insuranceType" value="Housing" />

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
        <p class="no-insurance-message">No Housing insurances found for the logged-in user.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
