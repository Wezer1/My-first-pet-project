package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")//данный класс возвращает SecurityUser, чтобы spring мог сравнить логин и пароль пользователя
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User doesn`t exit"));
        return SecurityUser.fromUser(user);
    }
}
