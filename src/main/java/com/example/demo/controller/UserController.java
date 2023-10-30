package com.example.demo.controller;

import com.example.demo.dto.User;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    @GetMapping("/{userId}")//обрабатывает только GET-запросы
    public ResponseEntity<User> getUser(@PathVariable Long userId) {//@PathVariable - получает из базы данных нужную инфу
        // В данном случае id

        // Здесь, в реальном приложении, вы бы обращались к сервису или репозиторию
        // для получения пользователя по его ID.
        // Ниже приведен пример с фиктивными данными.

        User user = new User(1,"John","Doe");
        user.setId(user.getId());//Я переделал метод получения(у меня же базы данных нет, поэтому просто сделал
        //присваивание нового id при создании нового user`a
        user.setName("John");
        user.setSurname("Doe");

        return ResponseEntity.ok(user);//возвращает успешный ответ HTTP 200 OK
        // с указанным объектом `user` в теле ответа.
    }
    @PostMapping("/user")//сериализация      десериализация
    public ResponseEntity<User> getInfo(@RequestBody User user){
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userList);
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
    public ResponseEntity<User> modifUser(@RequestBody User user){
        Optional<User> optionalUser = userList.stream()
                .filter(user2 -> user2.getId() == user.getId())
                .findFirst();
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            user1.updatedUser(user);
            return ResponseEntity.ok(user1);
        } else {
            return ResponseEntity.ok(optionalUser.get());
        }
    }
}

