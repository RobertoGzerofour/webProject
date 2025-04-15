package com.humber.WebProject.services;

import com.humber.WebProject.models.Order;
import com.humber.WebProject.models.User;
import com.humber.WebProject.repositores.OrderRepository;
import com.humber.WebProject.repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;



    public Order createOrder(Order order, String userEmail) {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        order.setUser(userOptional.get()); // ✅ Set the user in the order

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Long id, Order orderDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setTotalCost(orderDetails.getTotalCost());
            order.setAddress(orderDetails.getAddress());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
