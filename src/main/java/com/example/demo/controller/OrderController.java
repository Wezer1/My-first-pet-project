package com.example.demo.controller;

import com.example.demo.dto.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok(orderService.getAllUsers());
    }

    @GetMapping("/{ordersId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int ordersId){
        return ResponseEntity.ok(orderService.getOrderById(ordersId));
    }

    @DeleteMapping("/{ordersId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable int ordersId){
        orderService.deleteOrder(ordersId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<Order> addOrder(@RequestBody Order Order){
        return ResponseEntity.ok(orderService.saveOrder(Order));
    }

//    @PutMapping("/modifUser")
//    public ResponseEntity modifUser(@RequestBody User user){
//        Optional<User> optionalUser = userList.stream()
//                .filter(user2 -> user2.getId() == user.getId())
//                .findFirst();
//        if (optionalUser.isPresent()) {
//            User user1 = optionalUser.get();
//            user1.updatedUser(user);
//            return ResponseEntity.ok(user1);
//        } else {
//            String errorMessage = "{errorMessage: Пользователь с id " + user.getId() + " не найден}";
//            return ResponseEntity.badRequest().body(errorMessage);
//        }
//    }
}

