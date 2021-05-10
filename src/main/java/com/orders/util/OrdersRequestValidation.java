package com.orders.util;

import com.orders.model.ItemsRequest;
import com.orders.model.OrdersRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OrdersRequestValidation {

    public String validateRequest(OrdersRequest request) {
        if(request.getCustomerId() ==0
                 || request.getItems() == null) {
            return "Invalid or empty details in request";
        }

        if(request.getItems().size()<1) {
            return "Invalid Items in request";
        }

        for (ItemsRequest item: request.getItems()) {
            if(item.getItemId() ==0
                    || item.getQuantity() == 0) {
                return "Invalid or missing Item details";
            }
        }

        return null;
    }
}
