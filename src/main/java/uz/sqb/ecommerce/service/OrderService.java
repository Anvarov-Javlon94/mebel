package uz.sqb.ecommerce.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.sqb.ecommerce.entity.OrderDetails;
import uz.sqb.ecommerce.entity.Orders;
import uz.sqb.ecommerce.model.OrderDetailsModel;
import uz.sqb.ecommerce.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class OrderService {

    OrderRepository orderRepository;


    public Orders getOrderBySessionId(String sessionId){
        return orderRepository.getBySessionId(sessionId);
    }

    public void saveOrder(Orders orders){
        orderRepository.save(orders);
    }

    public void updateOrder(Orders orders, Long id){
        Orders order = orderRepository.getById(id);
        order.setAddress(orders.getAddress());
        order.setCity(orders.getCity());
        order.setAddress(orders.getAddress());
        order.setPhone(orders.getPhone());
        order.setName(orders.getName());
        orderRepository.save(order);
    }



    public List<Orders> findAll(){
        return orderRepository.findAll();
    }

    public  Orders getById(Long id){
        return orderRepository.getById(id);
    }


}
