/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.Facade;
import Logic.Layer;
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
public class PlaceOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegoHouseException {
        int length = (Integer)request.getSession().getAttribute("length");
        int width = (Integer)request.getSession().getAttribute("width");
        int layers = (Integer)request.getSession().getAttribute("layers");
        User user = (User)request.getSession().getAttribute("user");
        Order order = Facade.createOrder(length, width, layers, user);
        List<Layer> blocks = Facade.blockCreator(length, width, layers);
        List<Order> orders = Facade.getOrders(user);
        HttpSession session = request.getSession();
        session.setAttribute("blocks", blocks);
        session.setAttribute("order", order);
        session.setAttribute("orders", orders);
        return "invoice";
    }
    
}
