package uz.sqb.ecommerce.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.sqb.ecommerce.entity.OrderDetails;
import uz.sqb.ecommerce.model.OrderDetailsModel;
import uz.sqb.ecommerce.repository.OrderDetailsModelRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class OrderDetailsModelService {

    OrderDetailsModelRepository orderDetailsModelRepository;

    public OrderDetailsModel entityToModel(OrderDetails orderDetails){
        OrderDetailsModel orderDetailsModel = new OrderDetailsModel();
        orderDetailsModel.setOrder_id(orderDetails.getOrder().getId());
        orderDetailsModel.setOrder_pay_type(orderDetails.getOrder().getPay_type());
        orderDetailsModel.setOrder_address(orderDetails.getOrder().getAddress());
        orderDetailsModel.setOrder_phone(orderDetails.getOrder().getPhone());
        orderDetailsModel.setOrder_name(orderDetails.getOrder().getName());
        orderDetailsModel.setOrder_city(orderDetails.getOrder().getCity());
        orderDetailsModel.setOrder_date(orderDetails.getOrder().getDate());
        orderDetailsModel.setOrder_sessionId(orderDetails.getOrder().getSessionId());
        orderDetailsModel.setOrder_status(orderDetails.getOrder().getStatus());
        orderDetailsModel.setOrder_date_of_served(orderDetails.getOrder().getDate_of_served());
        orderDetailsModel.setProduct_id(orderDetails.getProduct().getId());
        orderDetailsModel.setProduct_photo(orderDetails.getProduct().getPhoto());
        orderDetailsModel.setProduct_name(orderDetails.getProduct().getName());
        orderDetailsModel.setProduct_price(orderDetails.getProduct().getPrice());
        orderDetailsModel.setProduct_register_date(orderDetails.getProduct().getRegister_date());
        orderDetailsModel.setProduct_product_character(orderDetails.getProduct().getProduct_character());
        orderDetailsModel.setProduct_product_description(orderDetails.getProduct().getProduct_description());
        orderDetailsModel.setProduct_view_count(orderDetails.getProduct().getView_count());
        orderDetailsModel.setProduct_category( orderDetails.getProduct().getCategory().getType_of_product());
        orderDetailsModel.setQuantity(orderDetails.getQuantity());
        orderDetailsModel.setTotal_price(orderDetails.getTotal_price());
        return orderDetailsModel;
    }



}
