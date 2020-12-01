<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <h2 class="align-content-center">Create new account</h2>
    <%
        String error =  request.getParameter("exist");
        if (error != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        User with this email exist
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%
        }
    %>
    <%
        String error2 =  request.getParameter("password");
        if (error2 != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Incorrect re-password
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%
        }
    %>
    <form action="/addUserServlet" method="post">
        <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp">
            <small id="emailHelp" class="form-text text-muted">Input your email</small>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password">
            <small id="passwordHelp" class="form-text text-muted">Input your password</small>
        </div>
        <div class="form-group">
            <label for="repassword">Repassword</label>
            <input type="password" class="form-control" id="repassword" name="repassword">
            <small id="repasswordHelp" class="form-text text-muted">Re-Input your password</small>
        </div>
        <div class="form-group">
            <label for="full_name">Full Name</label>
            <input type="text" class="form-control" id="full_name" name="full_name">
            <small id="full_nameHelp" class="form-text text-muted">Input your full name</small>
        </div>
        <div class="form-group">
            <label for="birth_date">Birthdate</label>
            <input type="date" class="form-control" id="birth_date" name="birth_date">
            <small id="birth_dateHelp" class="form-text text-muted">Input your birthdate</small>
        </div>
        <button type="submit" class="btn btn-primary">Sing Up</button>
    </form>
</div>
</body>
</html>
