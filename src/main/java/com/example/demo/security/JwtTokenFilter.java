package com.example.demo.security;

import com.example.demo.exceptions.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    // TODO: 03.04.2024 Вместо написания конструктора, пометь класс аннотацией RequiredArgsConstructor
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    // TODO: 29.03.2024 Я был не прав. Мы всегда будем попдать в этот фильтер, даже если мы помели путь до контроллера как permitAll. Чтобы корректно работал пришлось поменять метод resolveToken

    // TODO: 03.04.2024 Слишком много проверок в данном методе.
    //  С учетом того, что метод resolveToken будет возвращать токен, только если он верно написан, а в других случаях вернет null
    //  то тебе будет достаточно проверять на null и через validateToken.
    //  Если проверка прошла успешно, то кладешь authentication в SecurityContextHolder.
    //  Если проверка не прошла, то в блоке else кладешь HttpStatus.UNAUTHORIZED в httpServletResponse
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, JwtAuthenticationException {

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        //Изменил порядок проверок
        //Первая проверка для выброса UNAUTHORIZED
        if (token !=null &&!(jwtTokenProvider.validateToken(token))) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
        }
        //Вторая для корректного токена
        else if (token != null && jwtTokenProvider.validateToken(token)) { // TODO: 29.03.2024 Тут поменял местами этапы сравнения. Если у тебя может быть null, то всегд вначале проверяй на null, иначе у тебя возникнет NPE
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            //поскольку переписал метод и возвращаю при ошибке null сделал выброс ошибки
            else{
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
            }
        }
        //Третья для авторизации
        else if (token == null) {
            
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
