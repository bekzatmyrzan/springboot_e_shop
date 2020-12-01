<%@ page import="DB.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home page</title>
        <%@include file="includes/head.jsp"%>
    </head>
    <body>
        <%@include file="includes/navbar.jsp"%>
        <div class="container">
            <div class="row mt-5">
                <div class="col-sm-6 offset-3">
                    <%
                        Student student = (Student) request.getAttribute("studenttt");
                        if(student !=null){
                    %>
                    <%
                        String success = request.getParameter("success");
                        if(success!=null){
                    %>
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            Student saved successfully!
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    <%
                        }
                    %>
                    <form action="/saveStudent" method="post">
                        <input type="hidden" name="id" value="<%=student.getId()%>">
                        <div class="form-group">
                            <label>Name : </label>
                            <input type="text" name="name" class="form-control" value="<%=student.getName()%>">
                        </div>
                        <div class="form-group">
                            <label>Surname : </label>
                            <input type="text" name="surname" class="form-control" value="<%=student.getSurname()%>">
                        </div>
                        <div class="form-group">
                            <label>Middle name : </label>
                            <input type="text" name="middle_name" class="form-control" value="<%=student.getMiddle_name()%>">
                        </div>
                        <div class="form-group">
                            <label>Birthdate:</label>
                            <input type="date" name="birthdate" class="form-control" value="<%=student.getBirthdate()%>">
                        </div>
                        <div class="form-group">
                            <label>IIN : </label>
                            <input type="text" name="iin" class="form-control" value="<%=student.getIin()%>">
                        </div>
                        <label>Is Grant</label>
                        <select name="select" class="form-control">
                            <option<%= ((student.isGrant() == 1) ? "selected" :"")%> >YES</option>
                            <option>NO</option>
                        </select>
                        <div class="form-group">
                            <label>Specialty : </label>
                            <input type="text" name="specialty" class="form-control" value="<%=student.getSpecialty()%>">
                        </div>
                        <div class="form-group">
<%--                            <button type="button" class="btn btn-danger btn-sm float-right ml-2" data-toggle="modal" data-target="#exampleModal">--%>
<%--                                DELETE Student--%>
<%--                            </button>--%>
                            <button class="btn btn-success btn-sm float-right ml-2">SAVE Student</button>
                        </div>
                    </form>
                    <form action="/deleteStudent" method="post">
                        <input type="hidden" name="id" value="<%=student.getId()%>">
                        <button type="submit" class="btn btn-danger btn-sm float-right ml-1" data-toggle="modal" data-target="#exampleModal">DELETE</button>
                    </form>
                    <%
                        }else{
                    %>
                        <h1 class="text-center">404 Student NOT FOUND</h1>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
