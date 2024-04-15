package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exceptions.NoSuchException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> getAllOlders() {
        log.info("Get all Orders");
        if(orderRepository.findAll().isEmpty()){
            throw new NoSuchException("No orders");
        }
        return orderRepository.findAll().stream().map(orderMapper :: toDto).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Integer orderId) {
        log.info("Get order by id: {} ", orderId);
        Optional<com.example.demo.entity.Order> userOptional = Optional.ofNullable(orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchException("There is no order with ID = "+ orderId + " in Database")));
        return orderMapper.toDto(userOptional.get());
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        log.info("Saving order: {}", orderDTO);
        com.example.demo.entity.Order savedOrder = orderRepository.save(orderMapper.toEntity(orderDTO));
            return orderMapper.toDto(savedOrder);
    }

    public void deleteOrder(Integer orderId) {
        log.info("Delete order");
        if(orderRepository.findById(orderId).isEmpty()){
            throw new NoSuchException("There is no order with ID = "+ orderId + " in Database");
        }
        orderRepository.deleteById(orderId);
    }
}

