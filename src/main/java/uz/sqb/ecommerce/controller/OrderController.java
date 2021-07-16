package uz.sqb.ecommerce.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.sqb.ecommerce.entity.OrderDetails;
import uz.sqb.ecommerce.entity.Orders;
import uz.sqb.ecommerce.service.OrderDetailsService;
import uz.sqb.ecommerce.service.OrderService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class OrderController {

    OrderService orderService;
    OrderDetailsService orderDetailsService;

    public static final String ADD_TO_CART = "/addToCart";
    public static final String UPDATE_ORDER = "/updateOrder";
    public static final String GET_ORDER_BY_ID = "/getOrderById";
    public static final String GET_ORDER_LIST = "/getAllOrders";

    @PostMapping(ADD_TO_CART)
    public String createOrderForProduct(HttpSession session, Long id){
        orderDetailsService.createOrderForProduct(session, id);
        return "redirect:/getProductList";
    }

    @PostMapping(UPDATE_ORDER)
    public String updateOrder(@ModelAttribute @Valid Orders orders, BindingResult result, Model model, @RequestParam Long id){
        if (!result.hasErrors()){
            orderService.updateOrder(orders, id);
            return "check-send";
        } else {
            List<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsByOrderId(id);
            model.addAttribute("order_details", orderDetails);
            model.addAttribute("order_id", id);
            model.addAttribute("error", "error");
            return "checkout";
        }
    }

    @GetMapping(GET_ORDER_LIST)
    public String getAllOrders(Model model){
        List<Orders> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping(GET_ORDER_BY_ID)
    public String getOrderById(@RequestParam Long id, Model model){
        Orders order = orderService.getById(id);
        List<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsByOrderId(id);
        Double totalPrice = orderDetailsService.getTotalPrice(orderDetails);
        model.addAttribute("order_details", orderDetails);
        model.addAttribute("order", order);
        model.addAttribute("total_price", totalPrice);
        model.addAttribute("order_id", id);
        return "order-by-id";
    }



}
