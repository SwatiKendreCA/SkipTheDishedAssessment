package com.example.skipthedishes.controller;

import com.example.skipthedishes.model.DeliveryTransactions;
import com.example.skipthedishes.model.WeeklyCourierStatement;
import com.example.skipthedishes.services.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class CourierController {

    @Autowired
    CourierService courierService;

    @GetMapping("/delivery-transactions")
    public ResponseEntity<List<DeliveryTransactions>> getDeliveryTransactions(
            @RequestParam Integer courierId, @RequestParam Date startDate, @RequestParam Date endDate) {
        // Implement logic to fetch delivery transactions
        return ResponseEntity.ok(courierService.getDeliveryTransactions(courierId, startDate, endDate));
    }

    @GetMapping("/weekly-statement")
    public ResponseEntity<WeeklyCourierStatement> getWeeklyCourierStatement(@RequestParam Integer courierId) {
        // Implement logic to fetch weekly courier statement
        return ResponseEntity.ok(courierService.getWeeklyCourierStatement(courierId));
    }

}
