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
                Publication added successfully!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <form action="/addPublication" method="post">
                <div class="form-group">
                    <label>Name : </label>
                    <input type="text" name="name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Description : </label>
                    <input type="text" name="description" class="form-control">
                </div>
                <div class="form-group">
                    <label>Description : </label>
                    <input type="number" name="rating" class="form-control">
                </div>
                <div class="form-group">
                    <button class="btn btn-success float-right">Add Publication</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
