package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//обработка запроса HTTP и возвращение его в формате JSON лии XML
// каждый метод возвращает данные, которые будут преобразованы в JSON или XML и отправлены обратно клиенту
@RequestMapping("/api/orders")//Указатель мапинга между HTTP-запросом и методами обработки контроллера
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //сериализация      десериализация

    @GetMapping("/")
   // @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<OrderDTO>> getOrders(){
        return ResponseEntity.ok(orderService.getAllOlders());
    }

    @GetMapping("/{ordersId}")
  //  @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer ordersId){
        return ResponseEntity.ok(orderService.getOrderById(ordersId));
    }

    @DeleteMapping("/{ordersId}")
 //   @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable Integer ordersId){
        orderService.deleteOrder(ordersId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    //@PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO OrderDTO){
        return ResponseEntity.ok(orderService.saveOrder(OrderDTO));
    }
}

