<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contracts List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/tableStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/navbar.css">
</head>
<body>
<%-- NavBar inclusion --%>
<jsp:include page="/resources/layouts/navbar.jsp" />

<div class="table-container">
    <h1>Active Contracts</h1>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">
                ${successMessage}
        </div>
    </c:if>

    <table class="styled-table">
        <thead>
        <tr>
            <th>Contract ID</th>
            <th>Subscription Date</th>
            <th>Expiration Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contracts}">
            <tr>
                <td>${contract.id}</td>
                <td>${contract.subscriptionDate}</td>
                <td>${contract.expirationDate}</td>
                <td>${contract.status ? 'Active' : 'Expired'}</td>
                <td>
                    <div class="actions">
                        <a class="btn-generate" href="<c:url value='/contract/edit/${contract.id}'/>">Edit </a>
                        <form action="<c:url value='/contract/delete/${contract.id}'/>" method="post" onsubmit="return confirm('Are you sure you want to delete this contract?');" style="display:inline;">
                            <button type="submit" class="btn-delete">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty contracts}">
            <tr>
                <td colspan="5" class="no-insurance-message">No contracts available.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
