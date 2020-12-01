<%@ page import="java.util.ArrayList" %>
<%@ page import="DB.User" %>
<%@ page import="DB.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<%User user = (User) request.getSession().getAttribute("CURRENT_USER");%>
<div class="container">
    <div class="row">
        <div class="col-sm">
            <img src="<%=user.getPicture_url()%>" style="width: 18rem ;height: 18rem">
            <br>
            <div class="card" style="width: 18rem;">
                <div class="card-header">
                    <%=user.getFull_name()%>, <%=user.getAge()%> years
                </div>
                <ul class="list-group list-group-flush">
                    <a class="list-group-item" href="/myPageServlet">My profile</a>
                    <a class="list-group-item">Settings</a>
                    <a class="list-group-item" href="/logoutServlet">Log out</a>
                </ul>
            </div>
        </div>
        <div class="col-sm">
            <%
                String old_password_error = request.getParameter("old");
                if (old_password_error != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Incorrect old password
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String repassword = request.getParameter("repassword");
                if (repassword != null) {
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
            <h1>Edit Profile</h1>
            <form action="/updateUserServlet" method="post">
                <input type="hidden" name="sector" value="1">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" disabled="disabled" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                           value="<%=user.getEmail()%>">
                </div>
                <div class="form-group">
                    <label for="full_name">Full Name</label>
                    <input type="text" class="form-control" id="full_name" name="full_name"
                           value="<%=user.getFull_name()%>">
                </div>
                <div class="form-group">
                    <label for="birth_date">Birthdate</label>
                    <input type="date" class="form-control" id="birth_date" name="birth_date"
                           value="<%=user.getBirth_date()%>">
                </div>
                <button type="submit" class="btn btn-primary">Update profile</button>
            </form>
            <br>
            <label>Edit Picture</label>
            <form action="/updateUserServlet" method="post">
                <input type="hidden" name="sector" value="2">
                <div class="form-group">
                    <label for="picture_url">URL</label>
                    <input type="text" class="form-control" id="picture_url" name="picture_url"
                           value="<%=user.getPicture_url()%>">
                </div>
                <button type="submit" class="btn btn-primary">Update URL</button>
            </form>
            <br>
            <label>Update Password</label>
            <form action="/updateUserServlet" method="post">
                <input type="hidden" name="sector" value="3">
                <div class="form-group">
                    <label for="old_password">Old Password</label>
                    <input type="password" class="form-control" id="old_password" name="old_password">
                    <small id="old_passwordHelp" class="form-text text-muted">Input your old password</small>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                    <small id="passwordHelp" class="form-text text-muted">Input your new password</small>
                </div>
                <div class="form-group">
                    <label for="repassword">Repassword</label>
                    <input type="password" class="form-control" id="repassword" name="repassword">
                    <small id="repasswordHelp" class="form-text text-muted">Re-Input your new password</small>
                </div>
                <button type="submit" class="btn btn-primary">Update profile</button>
            </form>
        </div>
        <div class="col-sm">
            <div class="card" style="max-width: 18rem;">
                <div class="card-header" style="background-color: #17A2B8; color: white">Latest birthdays</div>
                <div class="card-body">
                    <%
                        ArrayList<User> latest_birthdays_user = (ArrayList<User>) request.getAttribute("latest_birthdays_user");
                        for (User user2 : latest_birthdays_user) {
                    %>
                    <p class="card-text"><%=user2.getFull_name()%>
                        , <%="" + user2.getBirth_date().charAt(8) + user2.getBirth_date().charAt(9)%> <%=user2.getMonth()%>
                    </p>
                    <%
                        }
                    %>
                </div>
            </div>
            <br>
            <div class="card" style="max-width: 18rem;">
                <div class="card-header" style="background-color: #17A2B8; color: white">My Games</div>
                <div class="card-body">
                    <h5 class="card-title">Football online</h5>
                    <h5 class="card-title">Ping Pong online</h5>
                    <h5 class="card-title">Chess masters</h5>
                    <h5 class="card-title">Races online</h5>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
