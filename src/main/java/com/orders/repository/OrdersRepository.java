package com.orders.repository;

import com.orders.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    public List<Orders> findAllByOrderNumber(int orderNumber);
}
