<%@ page import="DB.Student" %>
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
    <form action="/listAllNewsServlet" method="get">
        <div style="float: left">
            <label>Name</label>
            <input type="text" name="name" class="form-control">
        </div>
        <div style="float: left">
            <label>Surname</label>
            <input type="text" name="surname" class="form-control">
        </div>
        <div style="float: left">
            <label>IIN</label>
            <input type="number" name="iin" class="form-control">
        </div>
        <div style="float: left">
            <label>Is Grant</label>
            <select name="select" class="form-control">
                <option>YES</option>
                <option>NO</option>
                <option selected>-</option>
            </select>
        </div>
        <button type="submit" class="btn btn-dark">Filter</button>
    </form>

</div>
</body>
</html>
