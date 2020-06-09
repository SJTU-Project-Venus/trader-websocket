package com.sjtu.trade.daoimpl;

import com.sjtu.trade.dao.OrderDao;
import com.sjtu.trade.entity.Order;
import com.sjtu.trade.repository.OrderRepository;
import com.sjtu.trade.service.IDService;
import com.sjtu.trade.utils.OrderSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IDService idService;

    @Autowired
    private OrderSendService orderSendService;
    @Override
    public boolean Create(Order order){
        order.setId(idService.generate("order"));
        switch (order.getOrderType()){
            case MARKET:{
                orderSendService.TWAP_MARKET(order);
            }
            case LIMIT:{
                orderSendService.TWAP_LIMITTED(order);
            }
            case STOP:{
                orderSendService.TWAP_STOP(order);
            }
            case CANCEL:{
                orderSendService.TWAP_CANCEL(order);
            }
        }
        orderRepository.save(order);
        return true;
    }
}
