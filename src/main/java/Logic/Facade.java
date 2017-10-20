/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import DB.OrderMapper;
import DB.UserMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stanislav
 */
public class Facade {

    public static User login(String email, String password) throws LegoHouseException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password) throws LegoHouseException {
        User user = new User(email, password, "customer");
        UserMapper.createUser(user);
        return user;
    }

    public static Order createOrder(int length, int width, int layers, User user) throws LegoHouseException {
        Order order = new Order(length, width, layers);
        order = OrderMapper.createOrder(order, user);
        return order;
    }

    public static List<Order> getOrders(User user) throws LegoHouseException {
        List<Order> orders = OrderMapper.getAllOrders();

        if (user.getRole().equals("customer")) {
            orders = OrderMapper.getUserOrders(user);
        }
        return orders;
    }

    public static List<Layer> blockCreator(int length, int width, int layers) {
        List<Layer> blocks = BlockFactory.getBlocks(length, width, layers);
        return blocks;
    }

    public static Order getOrderForInvoice(int order_Id, List<Order> orders) {
        for (Order o : orders) {
            if (o.getOrder_id() == order_Id) {
                return o;
            }
        }
        return null;
    }

    public static void sendOrder(int order_id) throws LegoHouseException {
        OrderMapper.registerSentOrder(order_id);
    }
}
