<%-- 
    Document   : invoice
    Created on : 11-10-2017, 22:42:01
    Author     : Stanislav
--%>

<%@page import="Logic.User"%>
<%@page import="Logic.Layer"%>
<%@page import="Logic.HouseSide"%>
<%@page import="Logic.HouseSide"%>
<%@page import="java.util.List"%>
<%@page import="Logic.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>

        <!--BOOTSTRAP-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <div class="row">
            <div class="col-sm-4"></div>
            <h1 class="col-sm-4" style="text-align: center">Order receipt</h1>
            <!--USER INFO-->
            <div align="right" class="text-muted col-sm-4" padding="4px">
                <%User user = (User) request.getSession().getAttribute("user");%>
                <p><%= user.getEmail()%> is logged in</p>
            </div>
            <br>
        </div>

        <div class="row2">
            <div class="col-sm-12">
            <% Order order = (Order) request.getSession().getAttribute("order");%>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order date</th>
                        <th>length</th>
                        <th>width</th>
                        <th>layers</th>
                        <th>order status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><%=order.getOrder_id()%></td>
                        <td><%=order.getTimestamp()%></td>
                        <td><%=order.getLength()%></td>
                        <td><%=order.getWidth()%></td>
                        <td><%=order.getLayers()%></td>
                        <td><%=order.getOrderStatus()%></td>
                    </tr>
                </tbody>
            </table>
        </div>
        </div>

        <!-- CALCULATED ORDER -->
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4 well">
            <% List<Layer> house = (List<Layer>) request.getSession().getAttribute("house"); %>
            <table class="table table-striped" >
                <h3>Calculated Order</h3>
                <thead>
                    <tr>
                        <th>Brick type</th>
                        <th>Total amount</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- LEGO BRICK TABLE -->
                    <%int brick4x4 = 0;%>
                    <%int brick2x2 = 0;%>
                    <%int brick1x2 = 0;%>
                    <% for (int i = 0; i < house.size(); i++) { %>
                    <!-- LIST OF SIDES FOR ONE LAYER in order front, back, side1, side2 -->
                    <% List<HouseSide> houseSides = house.get(i).getSides();%>
                    <!-- BRICKS FOR SPECIFIC LAYER -->
                    <% for (HouseSide hs : houseSides) {
                            brick4x4 += hs.getBrick4x4();
                            brick2x2 += hs.getBrick2x2();
                            brick1x2 += hs.getBrick1x2();
                        }%>
                    <%}%>

                    <tr>
                        <td>Bricks 4x4</td>
                        <td><%=brick4x4%></td>
                    </tr>
                    <tr>
                        <td>Bricks 2x2</td>
                        <td><%=brick2x2%></td>
                    </tr>
                    <tr>
                        <td>Bricks 1x2</td>
                        <td><%=brick1x2%></td>
                    </tr>
                </tbody>
            </table>
            <%if (user.getRole().equals("customer")) {%>
            <form action="FrontController">
                <input type="hidden" name="command" value="redirectCustomerPage">
                <input class="btn btn-default" type="submit" value="Back to order page" />
            </form>
            <%} else { %>
            <form  action="FrontController">
                <input type="hidden" name="command" value="sendOrder">
                <div style="text-align: center">
                <input style="float: center" class="btn btn-default" type="submit" value="Confirm and send order" />
                </div>
                <br>
                <br>
                
            </form>
            </div>
            <div class="col-sm-4"></div>
        </div>

        <form action="FrontController">
            <input type="hidden" name="command" value="redirectEmployeePage">
            <input class="btn btn-default btn-block"type="submit" value="Back to Employee page" />
        </form> 

        <%}%>

        <!--BOOTSTRAP-->
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
