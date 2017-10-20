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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Stanislav
 */
public class Invoice extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegoHouseException {
        HttpSession session = request.getSession();
        List<Order> orders = (List<Order>)session.getAttribute("orders");
        Order order = Facade.getOrderForInvoice(Integer.parseInt(request.getParameter("specificInvoice")), orders);
        List<Layer> house = Facade.blockCreator(order.getLength(), order.getWidth(), order.getLayers());
        session.setAttribute("house", house);
        session.setAttribute("order", order); 
        return "invoice";
    }
    
}
