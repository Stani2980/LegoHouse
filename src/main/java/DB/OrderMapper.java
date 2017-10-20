/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Logic.LegoHouseException;
import Logic.Order;
import Logic.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stanislav
 */
public class OrderMapper {

    public static Order createOrder(Order order, User user) throws LegoHouseException {
        try {
            Connection conn = DBConnector.getConnection();
            String SQL = "INSERT INTO LegoHouse.Order (length, width, layers, user_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getLength());
            ps.setInt(2, order.getWidth());
            ps.setInt(3, order.getLayers());
            ps.setInt(4, user.getId());
            ps.executeUpdate();

            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            Order doneOrder = getCompletedOrder(id);
            return doneOrder;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LegoHouseException(ex.getMessage());
        }

    }

    private static Order getCompletedOrder(int orderId) throws LegoHouseException {
        try {
            Connection conn = DBConnector.getConnection();
            String SQL = "SELECT * FROM LegoHouse.Order WHERE order_id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            Order order = null;
            if (rs.next()) {
                Date timestamp = rs.getTimestamp("timestamp");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int layers = rs.getInt("layers");
                String orderStatus = rs.getString("orderStatus");
                order = new Order(length, width, layers);
                order.setOrder_id(orderId);
                order.setTimestamp(timestamp);
                order.setOrderStatus(orderStatus);
            }
            return order;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LegoHouseException(ex.getMessage());
        }
    }

    public static List<Order> getUserOrders(User user) throws LegoHouseException {
        try {
            Connection conn = DBConnector.getConnection();
            String SQL = "SELECT * FROM LegoHouse.Order WHERE user_id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                Date timestamp = rs.getTimestamp("timestamp");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int layers = rs.getInt("layers");
                String orderStatus = rs.getString("orderStatus");
                Order order = new Order(length, width, layers);
                order.setOrder_id(order_id);
                order.setTimestamp(timestamp);
                order.setOrderStatus(orderStatus);
                orders.add(order);
            }
            return orders;

        } catch (ClassNotFoundException | SQLException ex) {
            throw new LegoHouseException(ex.getMessage());
        }
    }
    
    public static void registerSentOrder(int order_id) throws LegoHouseException{
         try {
            Connection conn = DBConnector.getConnection();
            String SQL = "UPDATE LegoHouse.Order SET orderStatus = ? WHERE order_id= ?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "SENT");
            ps.setInt(2, order_id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new LegoHouseException(ex.getMessage());
        }
    }
    
    public static List<Order> getAllOrders() throws LegoHouseException {
         try {
            Connection conn = DBConnector.getConnection();
            String SQL = "SELECT * FROM LegoHouse.Order order by orderStatus";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                Date timestamp = rs.getTimestamp("timestamp");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int layers = rs.getInt("layers");
                String orderStatus = rs.getString("orderStatus");
                Order order = new Order(length, width, layers);
                order.setOrder_id(order_id);
                order.setTimestamp(timestamp);
                order.setOrderStatus(orderStatus);
                orders.add(order);
            }
            return orders;

        } catch (ClassNotFoundException | SQLException ex) {
            throw new LegoHouseException(ex.getMessage());
        }
    }
}
