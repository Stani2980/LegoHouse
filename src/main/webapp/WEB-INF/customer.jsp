<%-- 
    Document   : shop
    Created on : 11-10-2017, 22:41:24
    Author     : Stanislav
--%>

<%@page import="Logic.HouseSide"%>
<%@page import="Logic.Layer"%>
<%@page import="Logic.Order"%>
<%@page import="java.util.List"%>
<%@page import="Logic.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store</title>
        <!--BOOTSTRAP-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <div class="row">
            <div class="col-sm-4"></div>
            <h1 class="col-sm-4"style="text-align: center" >LegoHouse store</h1>
            <!--USER INFO-->
            <div align="right" class="text-muted col-sm-4" padding="4px">
                <%User user = (User) request.getSession().getAttribute("user");%>
                <p><%= user.getEmail()%> is logged in</p>
            </div>
            <br>
        </div>

        <div class="row">
            <div class="col-sm-4 well">
                <h3 align="center">Calculate order</h3>
                <table align="center">
                    <tr>
                        <td>
                            <form class="form-group" name="calculate" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="calculator">
                                Length(min 6):<br>
                                <input class="form-control" type="number" name="length" min="6" required placeholder=" input length">
                                <br>
                                Width (min 5):<br>
                                <input class="form-control" type="number" name="width" min="5" required placeholder="input width">
                                <br>
                                Layers :<br>
                                <input class="form-control" type="number" name="layers" required placeholder="input layers">
                                <br>
                                <input class="btn btn-default" type="submit" value="Calculate" name="calculate" formaction="FrontController" method="POST">

                            </form>
                        </td>
                    </tr>
                </table>
            </div>


            <div class="col-sm-4">
                <!-- CALCULATED ORDER -->
                <% if (request.getSession().getAttribute("house") != null) { %>
                <% List<Layer> house = (List<Layer>) request.getSession().getAttribute("house"); %>


                <table class="table table-striped" >
                    <h3 align="center">Calculated Order</h3>
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
                        
                <h5 class="well">
                    Bricks needed for order : 
                    <br>
                    Lenght = <%= request.getSession().getAttribute("length")%>
                    <br>
                    Width = <%= request.getSession().getAttribute("width")%>
                    <br>
                    Layers = <%= request.getSession().getAttribute("layers")%>
                    <br>
                    <br>
                    Press "Order" to confirm.
                </h5>

                <form name="order" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="placeOrder">
                    <input class="btn btn-default" type="submit" value="Order" name="Order"/>
                </form>
                <%}%>
            </div>

            <div class="col-sm-4 well">
                <!-- PREVIOUS ORDERS -->
                <% if (request.getSession().getAttribute("orders") != null) { %>
                <% List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");%>

                <h3 align="center">Previous Orders</h3>

                <!--LIST VIEW-->
                <ul class="list-group pre-scrollable">

                    <!--FOR EACH USER ORDER-->
                    <% for (Order order : orders) {%>
                    <li class="list-group-item padding: 5px 10px btn-default" style="font-size: 13px"><a href="FrontController?command=invoice&specificInvoice=<%=order.getOrder_id()%>"> ID: <%= order.getOrder_id()%> Order Status: <%= order.getOrderStatus()%> </a> </li>

                    <% }%>
                </ul>
            </div>
            <% }%>
        </div>

        <a href="index.jsp" class="btn btn-lg btn-default btn-block" stylerole="button"> Go to loginpage</a>

        <!--BOOTSTRAP-->
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
