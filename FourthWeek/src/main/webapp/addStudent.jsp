<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <div class="row mt-5">
        <div class="col-sm-6 offset-3">
            <%
                String success = request.getParameter("success");
                if (success != null) {
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Student added successfully!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <form action="/addStudent" method="post">
                <div class="form-group">
                    <label>Name : </label>
                    <input type="text" name="name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Surname : </label>
                    <input type="text" name="surname" class="form-control">
                </div>
                <div class="form-group">
                    <label>Middle name : </label>
                    <input type="text" name="middle_name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Birthdate:</label>
                    <input type="date" name="birthdate" class="form-control">
                </div>
                <div class="form-group">
                    <label>IIN : </label>
                    <input type="text" name="iin" class="form-control">
                </div>
                <label>Is Grant</label>
                <select name="select" class="form-control">
                    <option>YES</option>
                    <option>NO</option>
                    <option>-</option>
                </select>
                <div class="form-group">
                    <label>Specialty : </label>
                    <input type="text" name="specialty" class="form-control">
                </div>
                <div class="form-group">
                    <button class="btn btn-success float-right">Add Student</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
