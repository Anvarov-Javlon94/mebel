package uz.sqb.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.sqb.ecommerce.entity.OrderDetails;
import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @Query(value = "select * from order_details od inner join orders o on od.order_id = o.id where o.session_id = :session_id",nativeQuery = true)
    List<OrderDetails> getOrderDetailsByOrderSessionId(@Param("session_id") String sessionId);

    OrderDetails getByProduct_Id(Long id);

    @Query(value = "select * from order_details od inner join orders o on od.order_id = o.id where o.id = :id",nativeQuery = true)
    List<OrderDetails> getOrderDetailsByOrderId(@Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from order_details where  product_id = :id", nativeQuery = true)
    void deleteByProductId(@Param("id") Long id);


}
