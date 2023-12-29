package com.example.skipthedishes.repository;

import com.example.skipthedishes.entity.Adjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AdjustmentRepository extends JpaRepository<Adjustment, Long> {

    List<Adjustment> findByDeliveryId(Integer deliveryId);
}
