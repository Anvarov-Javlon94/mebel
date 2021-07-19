package uz.sqb.ecommerce.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String photo;

    @NotBlank(message = "Имя продукта обязателень")
    String name;

//    @NotNull(message = "Цена продукта обязателень")
    Double price;

    Date register_date;

    @NotBlank(message = "Характеристика продукта обязателень")
    @Column(length = 2000)
    String product_character;

    @Column(length = 2000)
    String product_description;

    Integer view_count;

    Boolean product_availability;

    //in percent %
    Double discount;
    Double after_discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id"))
    private Category category;


    public Product(String name, String product_character, Double price) {
        this.name = name;
        this.product_character = product_character;
        this.price = price;
    }
}
