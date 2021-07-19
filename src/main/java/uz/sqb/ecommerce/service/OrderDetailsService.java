package uz.sqb.ecommerce.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.sqb.ecommerce.entity.OrderDetails;
import uz.sqb.ecommerce.entity.Orders;
import uz.sqb.ecommerce.entity.Product;
import uz.sqb.ecommerce.model.OrderDetailsModel;
import uz.sqb.ecommerce.model.OrderModel;
import uz.sqb.ecommerce.repository.OrderDetailsModelRepository;
import uz.sqb.ecommerce.repository.OrderDetailsRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class OrderDetailsService {

    OrderDetailsRepository orderDetailsRepository;
    OrderService orderService;
    ProductService productService;
    OrderDetailsModelRepository orderDetailsModelRepository;
    OrderDetailsModelService orderDetailsModelService;

    public Double getTotalPrice(List<OrderDetails> ordersAndProducts) {
        Double totalPrice = 0.0;
        for (OrderDetails od : ordersAndProducts) {
            if (od.getProduct().getDiscount() == null){
                totalPrice += od.getQuantity() * od.getProduct().getPrice();
            } else {
                Double discountPrice = (od.getProduct().getPrice() * od.getProduct().getDiscount()) / 100;
                Double afterDiscountPrice = od.getProduct().getPrice() - discountPrice;
                totalPrice += od.getQuantity() * afterDiscountPrice;
            }

        }
        return totalPrice;
    }


    public OrderDetails createNewOrderDetails(Orders orderNew, Product product) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(orderNew);
        orderDetails.setProduct(product);
        orderDetails.setQuantity(1);
        if (product.getDiscount() != null){
            orderDetails.setTotal_price(1 * orderDetails.getProduct().getAfter_discount());
        } else {
            orderDetails.setTotal_price(1 * product.getPrice());
        }
        return orderDetails;
    }

    public void saveOrderDetails(OrderDetails orderDetails){
        orderDetailsRepository.save(orderDetails);
    }

    public OrderDetails getOrderDetailsByProductId(Long id){
        return orderDetailsRepository.getByProduct_Id(id);
    }

    public OrderDetails createOrderDetailsWithProduct(Date date, Orders order, Product product){
        OrderDetails orderDetails = new OrderDetails();
        order.setDate(date);
        orderDetails.setOrder(order);
        orderDetails.setProduct(product);
        orderDetails.setQuantity(1);
        if (product.getDiscount() != null){
            orderDetails.setTotal_price(1 * orderDetails.getProduct().getAfter_discount());
        } else {
            orderDetails.setTotal_price(1 * product.getPrice());
        }
        return orderDetails;
    }

    public OrderDetails saveByProductId(Date date, Orders order, Product product){
        OrderDetails productId = new OrderDetails();
        int quantity = productId.getQuantity();
        order.setDate(date);
        productId.setOrder(order);
        productId.setQuantity(quantity + 1);
        if (product.getDiscount() != null){
            productId.setTotal_price(productId.getQuantity() * product.getAfter_discount());
        } else {
            productId.setTotal_price(productId.getQuantity() * product.getPrice());
        }
         return orderDetailsRepository.save(productId);
    }

    public void createOrderForProduct(HttpSession session, Long id){
        Orders order = orderService.getOrderBySessionId(session.getId());
        Date date = new Date();
        if (order == null){
            Orders orderNew = new Orders(date, session.getId());
            orderService.saveOrder(orderNew);
            Product product = productService.getProductById(id);
            OrderDetails od = createNewOrderDetails(orderNew, product);
            saveOrderDetails(od);
            OrderDetailsModel odm = new OrderDetailsModel(od);
            orderDetailsModelRepository.save(odm);

        }
        else {
            List<OrderDetails> list = orderDetailsRepository.getOrderDetailsByOrderSessionId(session.getId());
            int x = 0;
            for (OrderDetails orderDetails : list){
                if (orderDetails.getProduct().getId().equals(id)){
                    x += 1;
                }
            }
            Product product = productService.getProductById(id);
            if (x == 0){
                OrderDetails orderDetails = createOrderDetailsWithProduct(date, order, product);
                saveOrderDetails(orderDetails);
                OrderDetailsModel model = orderDetailsModelService.entityToModel(orderDetails);
                orderDetailsModelRepository.save(model);
            }
            else {
                saveByProductId(date, order, product);
            }
        }
    }

    public List<OrderDetails> getOrderDetailsByOrderSessionId(String sessionId){
        return getSortedList(orderDetailsRepository.getOrderDetailsByOrderSessionId(sessionId));
    }

    public List<OrderDetails> getSortedList(List<OrderDetails> orderDetails){
        return orderDetails.stream()
                .sorted(Comparator.comparing(OrderDetails::getId))
                .collect(Collectors.toList());
    }



    public List<OrderDetails> getOrderDetailsByOrderId(Long id){
        return orderDetailsRepository.getOrderDetailsByOrderId(id);
    }

    public void putQuantityProduct(Long id, Integer quantity){
        OrderDetails orderDetails = orderDetailsRepository.getById(id);
        orderDetails.setQuantity(quantity);
        if (orderDetails.getProduct().getAfter_discount() != null){
            orderDetails.setTotal_price(quantity * orderDetails.getProduct().getAfter_discount());
        } else {
            orderDetails.setTotal_price(quantity * orderDetails.getProduct().getPrice());
        }
        orderDetailsRepository.save(orderDetails);
    }

    public void deleteProductFromCart(Long id){
        orderDetailsRepository.deleteByProductId(id);
    }

    public int getCartSize(HttpSession session) {
        List<OrderDetails> orderDetails = getOrderDetailsByOrderSessionId(session.getId());
        int cart_size = 0;
        for (OrderDetails orderDetail : orderDetails) {
            cart_size += orderDetail.getQuantity();
        }
        return cart_size;
    }

    public OrderDetails getById(Long id) {
        return orderDetailsRepository.getById(id);
    }


}
