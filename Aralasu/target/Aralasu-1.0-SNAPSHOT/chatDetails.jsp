<%@ page import="java.util.ArrayList" %>
<%@ page import="DB.User" %>
<%@ page import="DB.Post" %>
<%@ page import="DB.Chat" %>
<%@ page import="DB.Message" %>
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
            <%
                ArrayList<Message> messages = (ArrayList<Message>) request.getAttribute("messages");
                User another_user = null;
                if (messages != null) {
                    another_user = messages.get(0).getUser().getId().equals(currentUser.getId()) ? messages.get(0).getSender() : messages.get(0).getUser();
                    System.out.println(another_user.getFull_name() + "another user!!");
                    for (Message message : messages) {
            %>
            <li class="media">
                <img src="<%=message.getSender().getPicture_url()%>" class="mr-3" alt="..." height="100px"
                     style="border-radius: 100px;padding: 15px">
                <div class="media-body">
                    <h3 class="mt-0 mb-1"><%=message.getSender().getFull_name()%>
                    </h3>
                    <h5><%=message.getMessage_text()%></h5>
                    <p><%=message.getSent_date()%>
                    </p>
                </div>
            </li>
            <%
                    }
                }
            %>
            <form class="form-inline my-2 my-lg-0" action="/sendMessageServlet" method="post">
                <input type="hidden" name="opponent_user_id" value="<%=another_user.getId()%>">
                <input type="hidden" name="fromJSP" value="chatDetails">
                <input class="form-control" name="message_text" type="text" placeholder="Send Message">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Send</button>
            </form>
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
