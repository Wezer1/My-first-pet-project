package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//обработка запроса HTTP и возвращение его в формате JSON лии XML
// каждый метод возвращает данные, которые будут преобразованы в JSON или XML и отправлены обратно клиенту
@RequestMapping("/api/users")//Указатель мапинга между HTTP-запросом и методами обработки контроллера
public class UserController {



    private UserService userService;
    //сериализация      десериализация

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers(){
        List<User> list = userService.getAllUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable int userId){
        User user = userService.getUserById(userId);
        userService.deleteUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok(user);
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

