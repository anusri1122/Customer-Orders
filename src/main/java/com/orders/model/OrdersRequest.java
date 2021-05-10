package com.orders.model;

import java.util.List;

public class OrdersRequest {

    private int customerId;

    private List<ItemsRequest> items;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<ItemsRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemsRequest> items) {
        this.items = items;
    }
}
