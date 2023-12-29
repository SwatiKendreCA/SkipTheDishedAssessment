package com.example.skipthedishes.repository;

import com.example.skipthedishes.entity.CourierStatement;
import com.example.skipthedishes.services.CourierService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<CourierStatement, Long> {

    CourierStatement findByCourierId(Integer courierId);
}
