package uz.sqb.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.sqb.ecommerce.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p.id, p.photo, p.name, p.price, p.register_date, p.product_character, p.product_description, p.view_count, p.category_id from order_details od inner join product p on od.product_id = p.id inner join orders o on od.order_id = o.id where o.id = :id", nativeQuery = true)
    List<Product> getProductListByOrderId(@Param("id") Long id);
}
