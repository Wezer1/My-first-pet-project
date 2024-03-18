package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exceptions.NoSuchOrderException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public List<OrderDTO> getAllUsers() {
        log.info("Get all Orders: "); // TODO: 18.03.2024 лучше будет так  log.info("Get all Orders");
        if(orderRepository.findAll().isEmpty()){
            throw new NoSuchOrderException("No orders");
        }
        return orderRepository.findAll().stream().map(orderMapper :: toDto).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(int orderId) { // TODO: 18.03.2024 Замени тип на обертку Integer
        log.info("Get order by id: "); // TODO: 18.03.2024 лучше будет так  log.info("Get order by id: {} ", orderId); так ты будешь выводить в лог запрашиваемый id
        Optional<com.example.demo.entity.Order> userOptional = Optional.ofNullable(orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchOrderException("There is no order with ID = "+ orderId + " in Database")));
        return orderMapper.toDto(userOptional.get());

    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        log.info("Saving order: {}", orderDTO);
        com.example.demo.entity.Order savedOrder = orderRepository.save(orderMapper.toEntity(orderDTO));
            return orderMapper.toDto(savedOrder);
    }

    public void deleteOrder(int orderId) { // TODO: 18.03.2024 Замени тип на обертку Integer
        log.info("Delete order");
        if(orderRepository.findById(orderId).isEmpty()){
            throw new NoSuchOrderException("There is no order with ID = "+ orderId + " in Database");
        }
        orderRepository.deleteById(orderId);
    }
}

