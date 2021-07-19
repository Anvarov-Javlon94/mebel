package uz.sqb.ecommerce.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.sqb.ecommerce.entity.OrderDetails;
import uz.sqb.ecommerce.entity.Orders;
import uz.sqb.ecommerce.model.OrderDetailsModel;
import uz.sqb.ecommerce.repository.OrderDetailsModelRepository;
import uz.sqb.ecommerce.service.OrderDetailsService;
import uz.sqb.ecommerce.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CartController {

    OrderDetailsService orderDetailsService;
    OrderService orderService;
    OrderDetailsModelRepository odmRepository;

    public static final String GET_CART_BY_SESSION_ID = "/getCartBySessionId";
    public static final String DELETE_PRODUCT_FROM_CART = "/deleteProductFromCart";


    @GetMapping(GET_CART_BY_SESSION_ID)
    public String getOrderDetailsBySessionId(HttpSession session, Model model){
        Orders order = orderService.getOrderBySessionId(session.getId());
        List<OrderDetails> ordersAndProducts = orderDetailsService.getOrderDetailsByOrderSessionId(session.getId());
        Double totalPrice = orderDetailsService.getTotalPrice(ordersAndProducts);

        model.addAttribute("order", order);
        model.addAttribute("order_details", ordersAndProducts);
        model.addAttribute("total_price", totalPrice);
        return "cart-by-session-id";
    }

    @GetMapping(DELETE_PRODUCT_FROM_CART)
    public String deleteProductFromCart(@RequestParam Long id){
        orderDetailsService.deleteProductFromCart(id);
        return "redirect:/getCartBySessionId";
    }

    @GetMapping("/changeStatusOfCartToServed")
    public String changeStatusOfCartToServed(@RequestParam Long id){
        Date date = new Date();
        Orders order = orderService.getById(id);
        order.setDate_of_served(date);
        order.setStatus("SERVED");
        orderService.saveOrder(order);
        return "redirect:/getAllOrders";
    }

    @GetMapping("/changeStatusOfCartToCanceledByAdmin")
    public String changeStatusOfCartToCancelByAdmin(@RequestParam Long id){
        Date date = new Date();
        Orders order = orderService.getById(id);
        order.setDate_of_served(date);
        order.setStatus("CANCELED_BY_ADMIN");
        orderService.saveOrder(order);
        return "redirect:/getAllOrders";
    }

    @GetMapping("/changeStatusOfCartToCanceledByOrder")
    public String changeStatusOfCartToCancelByOrder(@RequestParam Long id){
        Date date = new Date();
        Orders order = orderService.getById(id);
        order.setDate_of_served(date);
        order.setStatus("CANCELED_BY_ORDER");
        orderService.saveOrder(order);
        return "redirect:/getAllOrders";
    }


    @GetMapping("/odmGetAll")
    public String odmGetAll(Model model){
        List<OrderDetailsModel> odmList = odmRepository.findAll();
        model.addAttribute("odmList", odmList);
        return "test5";
    }

}
