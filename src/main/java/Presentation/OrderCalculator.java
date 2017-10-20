/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Logic.Facade;
import Logic.Layer;
import Logic.LegoHouseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Stanislav
 */
public class OrderCalculator extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegoHouseException {
        int length = Integer.parseInt((request.getParameter("length")));
        int width = Integer.parseInt((request.getParameter("width")));
        int layers = Integer.parseInt((request.getParameter("layers")));
        List<Layer> house = Facade.blockCreator(length, width, layers);
        HttpSession session = request.getSession();
        session.setAttribute("length", length);
        session.setAttribute("width", width);
        session.setAttribute("layers", layers);
        session.setAttribute("house", house);
        return "customer";
    }
}
