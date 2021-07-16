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
import uz.sqb.ecommerce.service.OrderDetailsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CheckOutController {

    OrderDetailsService orderDetailsService;

    public static final String CHECKOUT = "/checkout";

    @GetMapping(CHECKOUT)
    public String checkOut(@RequestParam Long id, Model model){
        List<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsByOrderId(id);
        Double totalPrice = orderDetailsService.getTotalPrice(orderDetails);
        model.addAttribute("order_details", orderDetails);
        model.addAttribute("total_price", totalPrice);
        model.addAttribute("order_id", id);
        return "checkout";
    }

}
