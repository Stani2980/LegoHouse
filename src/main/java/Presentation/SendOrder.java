/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Stanislav
 */
public class SendOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegoHouseException {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");
        Facade.sendOrder(order.getOrder_id());
        List<Order> orders = Facade.getOrders((User)session.getAttribute("user"));
        session.setAttribute("orders", orders);
        return "employee";
    }
    
}
