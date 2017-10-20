<%-- 
    Document   : employee
    Created on : 12-10-2017, 00:06:40
    Author     : Stanislav
--%>

<%@page import="Logic.User"%>
<%@page import="Logic.Order"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee page</title>
        <!--BOOTSTRAP-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <div class="row">
            <div class="col-sm-4"></div>
            <h1 class="col-sm-4" style="text-align: center">Orders</h1>

            <!--USER INFO-->
            <div style="float: right" class="col-sm-4 text-muted">
                <%User user = (User) request.getSession().getAttribute("user");%>
                <p><%= user.getEmail()%> is logged in</p>
            </div>
            <br>
        </div>

        <h5 class="row well" style="text-align: center" >All orders not yet processed are shown on top
            <br>
            To confirm sent order, press on the order and press "Confirm and send"
        </h5>

        <!-- ALL PREVIOUS ORDERS -->
        <div class="row">
            <div class="col-sm-3"></div>
            <% if (request.getSession().getAttribute("orders") != null) { %>
            <% List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");%>
            <div class=" col-sm-6 well">
            <!--LIST VIEW-->
            <ul class="list-group pre-scrollable">

                <!--FOR EACH USER ORDER-->
                <% for (Order order : orders) {%>
                <li class="list-group-item padding: 5px 10px btn-default" style="font-size: 13px"><a href="FrontController?command=invoice&specificInvoice=<%=order.getOrder_id()%>"> ID: <%= order.getOrder_id()%> Order Status: <%= order.getOrderStatus()%> </a> </li>

                <% }%>
            </ul>

            <% }%>
            </div>
            <div class="col-sm-3"></div>
        </div>
        <a class="btn btn-default btn-block" href="index.jsp"> Go to loginpage</a>

        <!--BOOTSTRAP-->
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
