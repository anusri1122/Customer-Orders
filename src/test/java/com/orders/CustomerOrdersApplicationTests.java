package com.orders;


import com.orders.model.ItemsRequest;
import com.orders.model.OrdersRequest;
import com.orders.model.OrdersResponse;
import com.orders.repository.OrdersRepository;
import com.orders.service.OrdersService;
import com.orders.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AssertionErrors;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CustomerOrdersApplicationTests {

	@Autowired
	private OrdersService ordersService;

	@Test
	public void addOrders() {
		ResponseEntity<String> response = ordersService.addOrder(getOrdersRequest());
		Assertions.assertEquals("Successfully added order with Order number - 2", response.getBody().toString());
	}

	@Test
	public void getOrders() {
		OrdersResponse response = ordersService.findOrder(2);
		Assertions.assertEquals(100.0, response.getTotalAmount());
	}

	private OrdersRequest getOrdersRequest() {
		OrdersRequest request = new OrdersRequest();
		request.setCustomerId(1234);
		List<ItemsRequest> items = new ArrayList<>();
		ItemsRequest item = new ItemsRequest();
		item.setQuantity(2);
		item.setItemId(1001);
		items.add(item);
		request.setItems(items);
		return request;
	}

}
