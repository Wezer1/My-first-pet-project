package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//обработка запроса HTTP и возвращение его в формате JSON лии XML
// каждый метод возвращает данные, которые будут преобразованы в JSON или XML и отправлены обратно клиенту
@RequestMapping("/api/users")//Указатель мапинга между HTTP-запросом и методами обработки контроллера
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    //сериализация      десериализация

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{usersId}")
    public ResponseEntity<User> getUserById(@PathVariable int usersId){
        return ResponseEntity.ok(userService.getUserById(usersId));
    }

    @DeleteMapping("/{usersId}")
    public ResponseEntity<User> deleteUser(@PathVariable int usersId){
        userService.deleteUser(usersId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
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

