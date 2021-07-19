package uz.sqb.ecommerce.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.sqb.ecommerce.entity.Category;
import uz.sqb.ecommerce.entity.Product;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductModel {
    Long id;
    String photo;
    String name;
    Double price;
    Date register_date;
    String product_character;
    String product_description;
    Integer view_count;
    Category category;

    public static ProductModel getProductModel(Product product){
        ProductModel model = new ProductModel();
        model.setId(product.getId());
        model.setPhoto(product.getPhoto());
        model.setName(product.getName());
        model.setPrice(product.getPrice());
        model.setRegister_date(product.getRegister_date());
        model.setProduct_character(product.getProduct_character());
        model.setProduct_description(product.getProduct_description());
        model.setView_count(product.getView_count());
        model.setCategory(product.getCategory());
        return model;
    }
}
