<%@ page import="java.util.ArrayList" %>
<%@ page import="DB.Language" %>
<%@ page import="DB.DBManager" %>
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
                New added successfully!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <form action="/addNew" method="post">
                <div class="form-group">
                    <label>Title : </label>
                    <input type="text" name="title" class="form-control">
                </div>
                <div class="form-group">
                    <label>Short_content : </label>
                    <input type="text" name="short_content" class="form-control">
                </div>
                <div class="form-group">
                    <label>Post date : </label>
                    <input type="time" name="post_date" class="form-control">
                </div>
                <div class="form-group">
                    <label>Description : </label>
                    <input type="number" name="rating" class="form-control">
                </div>
                <div class="form-group">
                    <label>Picture url : </label>
                    <input type="text" name="picture_url" class="form-control">
                </div>
                <% ArrayList<Language> languages = DBManager.getLanguages();
                    for (Language language : languages) {
                %>
                <select name="language" class="form-control">
                    <option><%=language.getName()%></option>
                </select>
                <%
                    }
                %>
                <div class="form-group">
                    <button class="btn btn-success float-right">Add New</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
