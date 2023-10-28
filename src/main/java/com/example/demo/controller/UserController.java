package com.example.demo.controller;

import com.example.demo.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//обработка запроса HTTP и возвращение его в формате JSON лии XML
// каждый метод возвращает данные, которые будут преобразованы в JSON или XML и отправлены обратно клиенту
@RequestMapping("/api/users")//Указатель мапинга между HTTP-запросом и методами обработки контроллера
public class UserController {

    @GetMapping("/{userId}")//обрабатывает только GET-запросы
    public ResponseEntity<User> getUser(@PathVariable Long userId) {//@PathVariable - получает из базы данных нужную инфу
        // В данном случае id

        // Здесь, в реальном приложении, вы бы обращались к сервису или репозиторию
        // для получения пользователя по его ID.
        // Ниже приведен пример с фиктивными данными.

        User user = new User();
        user.setId(user.getId());//Я переделал метод получения(у меня же базы данных нет, поэтому просто сделал
        //присваивание нового id при создании нового user`a
        user.setName("John");
        user.setSurname("Doe");

        return ResponseEntity.ok(user);//возвращает успешный ответ HTTP 200 OK
        // с указанным объектом `user` в теле ответа.
    }
}

