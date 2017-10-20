<%-- 
    Document   : index
    Created on : 10-10-2017, 15:20:20
    Author     : Stanislav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lego House Store</title>

        <!--BOOTSTRAP-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <h1 align="center" class="row">Welcome to the LegoHouse store</h1>

        <div class="row">
            
            <%-- REGISTER FORM --%>
            <div class="col-sm-6">
                <table align="center" >
                 <h3 align="center">Login</h3>
                    <tr>
                        <td>
                            <form class="well form-group" name="login" action="FrontController" method="POST">
                                <input class="form-control" type="hidden" name="command" value="login">
                                Email:<br>
                                <input class="form-control" type="text" name="email" placeholder="email">
                                <br>
                                Password:<br>
                                <input class="form-control" type="password" name="password" placeholder="password">
                                <br>
                                <input class="btn btn-default" type="submit" value="Submit">
                            </form>
                        </td>
                    </tr>
                </table>
            </div>

            <%-- REGISTER FORM --%>
            <div class="col-sm-6">
                <table align="center">
                    <h3 align="center">Or Register</h3>
                    <tr>
                        <td>
                            <form class="well form-group" name="register" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="register">
                                Email:<br>
                                <input class="form-control" type="text" name="email" required placeholder="email">
                                <br>
                                Password:<br>
                                <input class="form-control" type="password" name="password1" required placeholder="password">
                                <br>
                                Retype Password:<br>
                                <input class="form-control" type="password" name="password2" required placeholder="confirm password">
                                <br>
                                <input class="btn btn-default" type="submit" value="Submit">
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <% String error = (String) request.getAttribute("error");
            if (error != null) {%>
        <H2>Error!!</h2>
        <p><%= error%></p>
        <% }%>

        <!--BOOTSTRAP-->
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
