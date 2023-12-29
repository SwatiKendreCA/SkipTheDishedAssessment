package com.example.skipthedishes.repository;

import com.example.skipthedishes.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

    List<Bonus> findByDeliveryId(Integer deliveryId);
}
