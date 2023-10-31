package com.example.demo.controller;

import com.example.demo.dto.User;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController//обработка запроса HTTP и возвращение его в формате JSON лии XML
// каждый метод возвращает данные, которые будут преобразованы в JSON или XML и отправлены обратно клиенту
@RequestMapping("/api/users")//Указатель мапинга между HTTP-запросом и методами обработки контроллера
public class UserController {

    private List<User> userList = new ArrayList<>();

    @PostConstruct
    public void init(){
        User user1 = new User(1,"Max","Chered");
        User user2 = new User(2,"Zaur","Tregulov");
        User user3 = new User(3,"Ivan","Ivanov");
        User user4 = new User(4,"Oleg","Malov");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
    }

    @PostMapping("/user")//сериализация      десериализация
    public ResponseEntity<User> getInfo(@RequestBody User user){
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUsers")
    public ResponseEntity getUsers(@PathVariable Long id){
        Optional<User> optionalUser = userList.stream()
                .filter(user2 -> user2.getId() == id)
                .findFirst();
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser);
        } else {
            String errorMessage = "{errorMessage: Пользователь с id " + id + " не найден}";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@PathVariable Long index){
        User temp = userList.get(Math.toIntExact(index));
        userList.remove(index);
        return ResponseEntity.ok(temp);
    }

    @PostMapping("/addfUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userList.add(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/modifUser")
    public ResponseEntity modifUser(@RequestBody User user){
        Optional<User> optionalUser = userList.stream()
                .filter(user2 -> user2.getId() == user.getId())
                .findFirst();
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            user1.updatedUser(user);
            return ResponseEntity.ok(user1);
        } else {
            String errorMessage = "{errorMessage: Пользователь с id " + user.getId() + " не найден}";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}

