package domain;

import ui.Input;

import java.util.Date;

public class Order {
    private int order_id;
    private int pizza_no;
    private int amount;
    private String customer_name;
    private String customer_phone;
    private Date order_time;
    private int pickup_time;
    private boolean removed;


    public Order(int pizza_no, int amount, String customer_name, String customer_phone, int pickup_time) {
        this.pizza_no = pizza_no;
        this.amount = amount;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.pickup_time = pickup_time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPizza_no() {
        return pizza_no;
    }

    public void setPizza_no(int pizza_no) {
        this.pizza_no = pizza_no;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public int getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(int pickup_time) {
        this.pickup_time = pickup_time;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", order_id=" + order_id +
                ", pizza_no=" + pizza_no +
                ", amount=" + amount +
                ", customer_name='" + customer_name + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", order_time=" + order_time +
                ", pickup_time=" + Input.getMinutesToTimeFormat(pickup_time) +
                ", removed=" + removed +
                '}';
    }
}
