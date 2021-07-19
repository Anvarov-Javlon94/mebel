package uz.sqb.ecommerce.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.sqb.ecommerce.entity.Orders;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderModel {

    Long id;
    String pay_type;
    String address;
    String phone;
    String name;
    String city;
    Date date;
    String sessionId;
    String status = "NOT_SERVED";
    Date date_of_served;

    public static OrderModel getOrderModel(Orders orders){
        OrderModel orderModel = new OrderModel();
        orderModel.setId(orders.getId());
        orderModel.setPay_type(orders.getPay_type());
        orderModel.setAddress(orders.getAddress());
        orderModel.setPhone(orders.getPhone());
        orderModel.setName(orders.getName());
        orderModel.setCity(orders.getCity());
        orderModel.setSessionId(orders.getSessionId());
        orderModel.setDate(orders.getDate());
        orderModel.setDate_of_served(orders.getDate_of_served());
        return orderModel;
    }

}
