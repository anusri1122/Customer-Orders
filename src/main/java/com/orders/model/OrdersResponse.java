package com.orders.model;

import java.util.List;

public class OrdersResponse {
    private int orderNumber;

    private int customerId;

    private float totalAmount ;

    private List<ItemsResponse> items;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ItemsResponse> getItems() {
        return items;
    }

    public void setItems(List<ItemsResponse> items) {
        this.items = items;
    }
}
