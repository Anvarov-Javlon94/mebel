package uz.sqb.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sqb.ecommerce.model.OrderDetailsModel;

public interface OrderDetailsModelRepository extends JpaRepository<OrderDetailsModel, Long> {

}
