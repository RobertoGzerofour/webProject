package com.humber.WebProject.controllers;

import com.humber.WebProject.models.Order;
import com.humber.WebProject.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(
            @RequestBody Order order,
            HttpServletRequest request
    ) {
        String userEmail = (String) request.getAttribute("email"); // ✅ Extract email from JWT claims
        Order savedOrder = orderService.createOrder(order, userEmail);

        Map<String, Object> response = new HashMap<>();
        response.put("id", savedOrder.getId());
        response.put("message", "Order created successfully");

        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
