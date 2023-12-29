package com.example.skipthedishes.repository;

import com.example.skipthedishes.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByCourierId(Integer courierId);

    @Query(value = "SELECT d from Delivery d where d.deliveryId = ?1 and d.createdTimestamp BETWEEN ?2 AND ?3")
    List<Delivery> findByCourierIdAndDate(@Param("deliveryId") Integer deliveryId, @Param("startDate") Date startDate, @Param("endDate")Date endDate);
}
