package uz.sqb.ecommerce.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.sqb.ecommerce.entity.Category;
import uz.sqb.ecommerce.entity.OrderDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class OrderDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long order_id;
    String order_pay_type;
    String order_address;
    String order_phone;
    String order_name;
    String order_city;
    Date order_date;
    String order_sessionId;
    String order_status = "NOT_SERVED";
    Date order_date_of_served;

    Double total_price;
    Integer quantity;

    Long product_id;
    String product_photo;
    String product_name;
    Double product_price;
    Date product_register_date;
    String product_product_character;
    String product_product_description;
    Integer product_view_count;
    String product_category;

    public OrderDetailsModel(OrderDetails orderDetails){
        this.order_id = orderDetails.getOrder().getId();
        this.order_pay_type = orderDetails.getOrder().getPay_type();
        this.order_address = orderDetails.getOrder().getAddress();
        this.order_phone = orderDetails.getOrder().getPhone();
        this.order_name = orderDetails.getOrder().getName();
        this.order_city = orderDetails.getOrder().getCity();
        this.order_date = orderDetails.getOrder().getDate();
        this.order_sessionId = orderDetails.getOrder().getSessionId();
        this.order_status = orderDetails.getOrder().getStatus();
        this.order_date_of_served = orderDetails.getOrder().getDate_of_served();
        this.product_id = orderDetails.getProduct().getId();
        this.product_photo = orderDetails.getProduct().getPhoto();
        this.product_name = orderDetails.getProduct().getName();
        this.product_price = orderDetails.getProduct().getPrice();
        this.product_register_date = orderDetails.getProduct().getRegister_date();
        this.product_product_character = orderDetails.getProduct().getProduct_character();
        this.product_product_description = orderDetails.getProduct().getProduct_description();
        this.product_view_count = orderDetails.getProduct().getView_count();
        this.product_category = orderDetails.getProduct().getCategory().getType_of_product();
        this.quantity = orderDetails.getQuantity();
        this.total_price = orderDetails.getTotal_price();
    }

    public static OrderDetailsModel orderDetailsModel(OrderDetails orderDetails){
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
