package com.orders.controller;

import com.orders.model.OrdersRequest;
import com.orders.model.OrdersResponse;
import com.orders.service.OrdersService;
import com.orders.util.Constants;
import com.orders.util.OrdersRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersRequestValidation ordersRequestValidation;

    /**
     *
     * @param ordersRequest
     * @return String
     */
    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrder(@RequestBody OrdersRequest ordersRequest) {
        String validation = ordersRequestValidation.validateRequest(ordersRequest);
        if(validation != null) {
            return new ResponseEntity<String>(validation,HttpStatus.BAD_REQUEST);
        }
        return ordersService.addOrder(ordersRequest);
    }

    /**
     *
     * @param orderNumber
     * @return Orders Information
     */
    @GetMapping("/getOrder/{orderNumber}")
    public ResponseEntity getOrder(@PathVariable("orderNumber") Integer orderNumber) {
        OrdersResponse response = ordersService.findOrder(orderNumber);
        if(response == null) {
            return new ResponseEntity<String>("No Orders Found with this order number",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<OrdersResponse>(response,HttpStatus.OK);
    }
}
