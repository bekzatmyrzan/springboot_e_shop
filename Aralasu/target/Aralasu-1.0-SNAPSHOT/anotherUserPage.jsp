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
<%
    User another_user = (User) request.getAttribute("another_user");
    User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
%>
<div class="container">
    <div class="row">
        <div class="col-3">
            <img src="<%=another_user.getPicture_url()%>" class="card-img-top" alt="..." height="250 px">
            <br>
            <div class="card">
                <div class="card-header">
                    <%=another_user.getFull_name()%>, <%=another_user.getAge()%> years
                </div>
                <ul class="list-group list-group-flush">
                    <%
                        if ((int) request.getAttribute("isFriends") != 0) {
                    %>
                    <form action="/removeFromFriendsServlet" method="post" id="removeFromFriends">
                        <input type="hidden" name="user_id" value="<%=currentUser.getId()%>">
                        <input type="hidden" name="another_user_id" value="<%=another_user.getId()%>">
                        <a class="list-group-item" type="submit" id="idForA">Remove From Friends</a>
                    </form>
                    <%
                    } else {
                    %>
                    <a class="list-group-item" href="/myPageServlet">My profile</a>
                    <%
                        }
                    %>
                    <a class="list-group-item" data-toggle="modal" data-target="#sendMessageModal">Send Message
                    </a>
                    <div class="modal fade" id="sendMessageModal" tabindex="-1" role="dialog"
                         aria-labelledby="sendMessageModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form method="POST" action="/sendMessageServlet" id="send_form">
                                    <input type="hidden" name="fromJSP" value="anotherUserPage">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="sendMessageModalLabel">Send Message
                                            to <%=another_user.getFull_name()%>
                                        </h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="message_text"
                                                   name="message_text"
                                                   placeholder="Text...">
                                            <input type="hidden" name="opponent_user_id"
                                                   value="<%=another_user.getId()%>">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-dark">Send</button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <a class="list-group-item" href="/logoutServlet">Log out</a>
                </ul>
            </div>
        </div>
        <div class="col-6">
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
<script type="text/javascript">
    document.getElementById("idForA").onclick = function() {
        document.getElementById("removeFromFriends").submit();
    }
</script>
</html>
