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
            <%
                String success = request.getParameter("success");
                if (success != null) {
                    ;
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Request has been sent
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true"></span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String addsuccess = request.getParameter("addsuccess");
                if (addsuccess != null) {
                    ;
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Added friend successfully
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true"></span>
                </button>
            </div>
            <%
                }
            %>
            <%
                String rejsuccess = request.getParameter("rejsuccess");
                if (rejsuccess != null) {
                    ;
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Request tejected successfully
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true"></span>
                </button>
            </div>
            <%
                }
            %>

            <form class="form-inline my-2 my-lg-0" action="/friendsServlet" method="get">
                <input class="form-control" name="search" type="search" placeholder="Search friends"
                       aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>

            <%
                ArrayList<User> searchUsers = (ArrayList<User>) request.getAttribute("searchUsers");
                if (searchUsers.size() != 0) {
            %>
            <h2>Search results</h2>
            <%
                for (User searchUser : searchUsers) {
            %>
            <div class="card mb-3" style="margin-top: 20px">
                <div class="row no-gutters">
                    <div class="col-md-3">
                        <a href="/anotherUserPageServlet?another_user_id=<%=searchUser.getId()%>">
                            <img src="<%=searchUser.getPicture_url()%>" class="card-img" height="135px"
                                 style="border-radius: 100px;padding: 15px">
                        </a>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <a href="/anotherUserPageServlet?another_user_id=<%=searchUser.getId()%>"><%=searchUser.getFull_name()%>
                            </a>
                            <p class="card-text"><%=searchUser.getAge()%> years old</p>
                            <form action="/sendRequestServlet" method="post">
                                <input type="hidden" name="another_user_id" value="<%=searchUser.getId()%>">
                                <button type="submit" class="btn btn-secondary mr-2">Add to Friends</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            } else {
            %>

            <%
                ArrayList<User> friendsRequests = (ArrayList<User>) request.getAttribute("friendsRequests");
                if (friendsRequests.size() != 0) {
            %>
            <div class="card" style="margin-top: 20px">
                <h5 class="card-header">You have <%=friendsRequests.size()%> new request</h5>
                <div class="card-body">

                    <%
                        for (User u : friendsRequests) {
                    %>
                    <div class="card mb-3" style="margin-top: 20px">
                        <div class="row no-gutters">
                            <div class="col-md-3">
                                <a href="/anotherUserPageServlet?another_user_id=<%=u.getId()%>">
                                    <img src="<%=u.getPicture_url()%>" class="card-img" height="135px"
                                         style="border-radius: 100px;padding: 15px">
                                </a>
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <a href="/anotherUserPageServlet?another_user_id=<%=u.getId()%>"><%=u.getFull_name()%>
                                    </a>
                                    <p class="card-text"><small class="text-muted"><%=u.getAge()%> years old</small></p>
                                    <form action="/addToFriendsServlet" method="post">
                                        <input type="hidden" name="friend_id" value="<%=u.getId()%>">
                                        <button class="btn btn-success btn-sm"
                                                style="background-color: white; color: green; border: 2px solid #180B7A;">
                                            Confirm
                                        </button>
                                    </form>
                                    <form action="/rejectServlet" method="post">
                                        <input type="hidden" name="sender_id" value="<%=u.getId()%>">
                                        <button class="btn btn-success btn-sm"
                                                style="background-color: white; color: red; border: 2px solid #180B7A;">
                                            Reject
                                        </button>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>

                    <%
                        }
                    %>
                </div>
            </div>
            <%
                }
                ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
                if (friends != null) {
                    for (User f : friends) {
            %>
            <div class="card mb-3" style="margin-top: 20px">
                <div class="row no-gutters">
                    <div class="col-md-3">
                        <a href="/anotherUserPageServlet?another_user_id=<%=f.getId()%>">
                            <img src="<%=f.getPicture_url()%>" class="card-img" alt="..." height="135px"
                                 style="border-radius: 100px;padding: 15px">
                        </a>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <a href="/anotherUserPageServlet?another_user_id=<%=f.getId()%>"><%=f.getFull_name()%>
                            </a>
                            <p class="card-text"><small class="text-muted"><%=f.getAge()%> years old</small></p>
                            <button class="btn btn-success btn-sm"
                                    style="background-color: white; color: #180B7A; border: 2px solid #180B7A;">Send
                                Message
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <%
                        }
                    }
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
