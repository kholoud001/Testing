<%--
  Created by IntelliJ IDEA.
  User: youcode
  Date: 10/22/2024
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/authStyles.css">

    <title>Register Page</title>
</head>
<body>
<%--NavBar--%>
<div class="container">
    <div class="screen">
        <div class="screen__content">
            <form class="register" method="post" action="${pageContext.request.contextPath}/register">
                <div class="login__field">
                    <i class="login__icon fas fa-user"></i>
                    <input name="name" type="text" class="login__input" placeholder="User name" required>
                </div>
                <div class="login__field">
                    <i class="login__icon fas fa-user"></i>
                    <input name="email" type="email" class="login__input" placeholder="Email" required>
                </div>
                <div class="login__field">
                    <i class="login__icon fas fa-user"></i>
                    <input name="address" type="text" class="login__input" placeholder="Address">
                </div>
                <div class="login__field">
                    <i class="login__icon fas fa-user"></i>
                    <input name="phone" type="text" class="login__input" placeholder="Phone Number">
                </div>
                <div class="login__field">
                    <i class="login__icon fas fa-lock"></i>
                    <input name="password" type="password" class="login__input" placeholder="Password" required>
                </div>
                <button class="button login__submit" type="submit">
                    <span class="button__text">Register Now</span>
                    <i class="button__icon fas fa-chevron-right"></i>
                </button>
            </form>

        </div>
        <div class="screen__background">
            <span class="screen__background__shape screen__background__shape4"></span>
            <span class="screen__background__shape screen__background__shape3"></span>
            <span class="screen__background__shape screen__background__shape2"></span>
            <span class="screen__background__shape screen__background__shape1"></span>
        </div>
    </div>
</div>
</body>
</html>
