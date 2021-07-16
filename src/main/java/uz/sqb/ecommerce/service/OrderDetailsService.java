package uz.sqb.ecommerce.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.sqb.ecommerce.entity.OrderDetails;
import uz.sqb.ecommerce.entity.Orders;
import uz.sqb.ecommerce.entity.Product;
import uz.sqb.ecommerce.repository.OrderDetailsRepository;

import javax.servlet.http.HttpSession;
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

    public void saveByProductId(OrderDetails productId, Date date, Orders order, Product product){
        int quantity = productId.getQuantity();
        order.setDate(date);
        productId.setOrder(order);
        productId.setQuantity(quantity + 1);
        if (product.getDiscount() != null){
            productId.setTotal_price(productId.getQuantity() * product.getAfter_discount());
        } else {
            productId.setTotal_price(productId.getQuantity() * product.getPrice());
        }
        orderDetailsRepository.save(productId);
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
        }
        else {
            OrderDetails productId = getOrderDetailsByProductId(id);
            Product product = productService.getProductById(id);
            if (productId == null){
                OrderDetails orderDetails = createOrderDetailsWithProduct(date, order, product);
                saveOrderDetails(orderDetails);
            }
            else {
                saveByProductId(productId, date, order, product);
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
}
