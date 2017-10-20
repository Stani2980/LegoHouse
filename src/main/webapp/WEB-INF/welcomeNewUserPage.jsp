<%-- 
    Document   : welcomeNewUserPage
    Created on : 12-10-2017, 00:39:16
    Author     : Stanislav
--%>

<%@page import="Logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% User user = (User) request.getSession().getAttribute("user");%>
        <title>Welcome <%=user.getEmail()%></title>

        <!--BOOTSTRAP-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <h1 class="h1" align="center">You have sucessfully registered!</h1>
        <br>
        <br>
        <br>
        <br>
        <br>
        
        <a href="index.jsp" class="btn btn-default btn-block">Redirect to Login</a>

        <!--BOOTSTRAP-->
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
