<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Automobile Insurances</title>
    <!-- Linking to external CSS files for consistent styling -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/tableStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">
</head>
<body>
<%-- NavBar inclusion --%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<h1>Your Automobile Insurances</h1>

<c:choose>
    <c:when test="${not empty automobileInsurances}">
        <div class="table-container">
            <table class="styled-table">
                <thead>
                <tr>
<%--                    <th> Id</th>--%>
                    <th>Driver Age</th>
                    <th>Vehicle Use</th>
                    <th>Driving History</th>
                    <th>Price (MAD)</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="insurance" items="${automobileInsurances}">
                    <tr>
<%--                        <td><c:out value="${insurance.id}"/></td>--%>
                        <td><c:out value="${insurance.driverAge}"/></td>
                        <td><c:out value="${insurance.vehicleUse}"/></td>
                        <td><c:out value="${insurance.drivingHistory}"/></td>
                        <td><c:out value="${insurance.price}"/> MAD</td>
                        <td>
                            <!-- Delete action button -->
                            <form action="${pageContext.request.contextPath}/automobile/delete" method="post" style="display:inline;">
                                <input type="hidden" name="insuranceId" value="${insurance.id}" />
                                <button type="submit" class="btn-delete">Delete</button>
                            </form>

                            <!-- Show Contract form action button -->
                            <form action="${pageContext.request.contextPath}/contract/automobile" method="get" style="display:inline;">
                                <input type="hidden" name="insuranceId" value="${insurance.id}" />
                                <input type="hidden" name="insuranceType" value="Automobile" />
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
        <p class="no-insurance-message">No automobile insurances found for the logged-in user.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
