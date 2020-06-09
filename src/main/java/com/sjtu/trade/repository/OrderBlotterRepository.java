package com.sjtu.trade.repository;

import com.sjtu.trade.entity.Order;
import com.sjtu.trade.entity.OrderBlotter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderBlotterRepository extends MongoRepository<OrderBlotter, Long> {
     List<OrderBlotter> findByInitiatorId(Long id);
}
