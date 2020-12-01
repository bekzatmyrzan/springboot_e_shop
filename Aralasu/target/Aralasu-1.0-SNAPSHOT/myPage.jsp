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
<%User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");%>
<div class="container">
    <div class="row">
        <div class="col-3">
            <img src="<%=currentUser.getPicture_url()%>" class="card-img-top" alt="..." height="250 px">
            <br>
            <div class="card">
                <div class="card-header">
                    <%=currentUser.getFull_name()%>, <%=currentUser.getAge()%> years
                </div>
                <ul class="list-group list-group-flush">
                    <a class="list-group-item" href="/myPageServlet">My profile</a>
                    <a class="list-group-item">Settings</a>
                    <a class="list-group-item" href="/logoutServlet">Log out</a>
                </ul>
            </div>
        </div>
        <div class="col-6">
            <button class="btn btn-primary btn-lg btn-block"
                    data-toggle="modal" data-target="#addPostModal">Add New Post
            </button>
            <br>
            <div class="modal fade" id="addPostModal" tabindex="-1" role="dialog"
                 aria-labelledby="addPostModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form method="POST" action="/addPostServlet?author_id=<%=currentUser.getId()%>" id="add_form">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addPostModalLabel">Add New Post</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="title">Title:</label>
                                    <input type="text" class="form-control" id="title" name="title"
                                           placeholder="Title...">
                                </div>
                                <div class="form-group">
                                    <label for="short_content">Short Content:</label>
                                    <textarea type="text" class="form-control" id="short_content"
                                              name="short_content" placeholder="Short Content..."> </textarea>
                                </div>
                                <div class="form-group">
                                    <label for="content">Content:</label>
                                    <textarea type="text" class="form-control" id="content"
                                              name="content" placeholder="Content..."> </textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-dark">Add</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%
                ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
                for (Post post : posts) {
            %>
            <div class="card text-left">
                <div class="card-body">
                    <h5 class="card-title"><%=post.getTitle()%>
                    </h5>
                    <p class="card-text"><%=post.getShort_content()%>
                    </p>
                    <a href="#" class="btn btn-primary">More</a>
                </div>
                <div class="card-footer text-muted"><%=post.getPost_date()%> , by <%=post.getAuthor().getFull_name()%>
                </div>
            </div>
            <br>
            <%
                }
            %>
        </div>
        <div class="col-3">
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
