<%@ page import="java.util.ArrayList" %>
<%@ page import="DB.User" %>
<%@ page import="DB.Post" %>
<%@ page import="DB.Chat" %>
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
            <form class="form-inline my-2 my-lg-0" action="/friendsServlet" method="get">
                <input class="form-control" name="search" type="search" placeholder="Search friends"
                       aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
            <%
                ArrayList<Chat> chats = (ArrayList<Chat>) request.getAttribute("chats");
                if (chats != null) {
                    for (Chat chat : chats) {
            %>
            <div class="card mb-3"
                 style="margin-top: 20px<%=chat.getCountOfNewMessage()==0 ? "":";background-color: #c3e6cb"%>">
                <div class="row no-gutters">
                    <div class="col-md-3">
                        <a href="/anotherUserPageServlet?another_user_id=<%=chat.getOpponent_user().getId()%>">
                            <img src="<%=chat.getOpponent_user().getPicture_url()%>" class="card-img" alt="..."
                                 height="135px"
                                 style="border-radius: 100px;padding: 15px">
                        </a>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <a href="/chatDetailsServlet?chat_id=<%=chat.getId()%>"><%=chat.getOpponent_user().getFull_name()%>
                            </a>
                            <p class="card-text"><small class="text-muted"><%=chat.getLatest_message_text()%>
                            </small></p>
                        </div>
                        <div class="card-footer text-muted">
                            <%=chat.getLatest_message_time()%>
                        </div>
                    </div>
                </div>
            </div>
            <%
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
