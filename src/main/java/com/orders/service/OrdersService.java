package com.orders.service;

import com.orders.entities.Items;
import com.orders.entities.Orders;
import com.orders.model.ItemsRequest;
import com.orders.model.ItemsResponse;
import com.orders.model.OrdersRequest;
import com.orders.model.OrdersResponse;
import com.orders.repository.ItemsRepository;
import com.orders.repository.OrdersRepository;
import com.orders.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    private static Map<Integer,Items> itemList = null;
    private static final AtomicInteger counter = new AtomicInteger(1);

    /**
     *
     * @param ordersRequest
     * Retrieves the item list and save them in inmemory static variables so that they are retrieved only once.
     * For each item total amount is calculated and inserted in order tables
     * @return
     */
    public ResponseEntity<String> addOrder(OrdersRequest ordersRequest) {

        if(itemList == null) {
            itemList = itemsRepository.findAll().stream().collect(Collectors.toMap(Items::getItemId, Function.identity()));
        }
        int orderNumber = counter.incrementAndGet();

        try {

            for (ItemsRequest item: ordersRequest.getItems()) {
                if(!itemList.containsKey(item.getItemId())) {
                    return new ResponseEntity<>("Item with item id - " + item.getItemId() + " doesn't exists in inventory",HttpStatus.BAD_REQUEST);
                }
                Orders order = new Orders();
                order.setOrderNumber(orderNumber);
                order.setCustomerId(ordersRequest.getCustomerId());
                order.setTotalAmount(item.getQuantity() * itemList.get(item.getItemId()).getItemPrice());
                order.setItemId(item.getItemId());
                order.setQuantity(item.getQuantity());
                ordersRepository.save(order);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Successfully added order with Order number - " + orderNumber,HttpStatus.OK);

    }

    /**
     *
     * @param orderNumber
     * Retrives all the orders associated with this order and groups all the items and send order response
     * @return
     */
    public OrdersResponse findOrder(int orderNumber) {

        if(itemList == null) {
            itemList = itemsRepository.findAll().stream().collect(Collectors.toMap(Items::getItemId, Function.identity()));
        }

        List<Orders> orders = ordersRepository.findAllByOrderNumber(orderNumber);

        if(orders == null || orders.isEmpty()) {
            return null;
        }

        double totalAmount = 0;

        OrdersResponse response = new OrdersResponse();
        response.setOrderNumber(orders.get(0).getOrderNumber());
        response.setCustomerId(orders.get(0).getCustomerId());

        List<ItemsResponse> items = new ArrayList<>();

        for (Orders order:orders) {
            totalAmount = totalAmount + order.getTotalAmount();
            ItemsResponse item = new ItemsResponse();
            item.setItemId(order.getItemId());
            item.setItemName(itemList.get(order.getItemId()).getItemName());
            item.setItemPrice((float) itemList.get(order.getItemId()).getItemPrice());
            item.setQuantity(order.getQuantity());
            items.add(item);
        }
        response.setTotalAmount((float) totalAmount);
        response.setItems(items);
        return response;
    }

}
