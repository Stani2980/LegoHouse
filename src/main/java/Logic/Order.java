/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Date;

/**
 *
 * @author Stanislav
 */
public class Order {

    private int order_id;
    private Date timestamp;
    private int length;
    private int width;
    private int layers;
    private String orderStatus;

    public Order(int length, int width, int layers) {
        this.length = length;
        this.width = width;
        this.layers = layers;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getLayers() {
        return layers;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", timestamp=" + timestamp + ", length=" + length + ", width=" + width + ", layers=" + layers + ", orderStatus=" + orderStatus + '}';
    }
}
