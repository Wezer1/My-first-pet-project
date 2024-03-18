package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
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
    public ResponseEntity<List<OrderDTO>> getOrders(){
        return ResponseEntity.ok(orderService.getAllUsers());
    }

    @GetMapping("/{ordersId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int ordersId){ // TODO: 18.03.2024 Замени тип на обертку Integer, это нужно, так как если туда ничего не придет, то java туда сама запихнет 0
        return ResponseEntity.ok(orderService.getOrderById(ordersId));
    }

    @DeleteMapping("/{ordersId}")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable int ordersId){ // TODO: 18.03.2024 Замени тип на обертку Integer
        orderService.deleteOrder(ordersId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO OrderDTO){
        return ResponseEntity.ok(orderService.saveOrder(OrderDTO));
    }

    // TODO: 18.03.2024 Удали закомментированный код
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

