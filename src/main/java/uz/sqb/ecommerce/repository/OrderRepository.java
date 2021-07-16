package uz.sqb.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sqb.ecommerce.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders getBySessionId(String sessionId);
}
