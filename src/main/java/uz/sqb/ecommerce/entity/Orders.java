package uz.sqb.ecommerce.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Orders(String name, String city, String phone) {
        this.name = name;
        this.city = city;
        this.phone = phone;
    }

    public Orders(Date date, String sessionId) {
        this.date = date;
        this.sessionId = sessionId;
    }
}
