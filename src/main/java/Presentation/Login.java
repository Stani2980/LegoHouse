package Presentation;

import Logic.Facade;
import Logic.LegoHouseException;
import Logic.Order;
import Logic.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 The purpose of Login is to...

 @author kasper
 */
public class Login extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LegoHouseException {
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        User user = Facade.login( email, password );
        List<Order> orders = Facade.getOrders(user);
        HttpSession session = request.getSession();
        session.setAttribute("orders", orders);
        session.setAttribute( "user", user );
        session.setAttribute( "role", user.getRole() );
        return user.getRole();
    }

}
