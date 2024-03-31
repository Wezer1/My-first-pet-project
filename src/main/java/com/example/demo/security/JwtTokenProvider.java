package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.exceptions.JwtAuthenticationException;
import com.example.demo.model.Role;
import com.example.demo.service.UserService;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import liquibase.util.StringUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.expiration}")
    private long validitySeconds;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getRole().toString());
        claims.put("user_id", user.getId());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validitySeconds * 1000);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        StringBuilder stringBuilder = new StringBuilder(token);
        stringBuilder.insert(0,"Bearer");
        return stringBuilder.toString();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            throw new JwtAuthenticationException("Ваш токен авторизации не валиден");
        }
    }

    // TODO: 29.03.2024 Теперь мы выбрасываем ошибку, только если у нас есть токен, но он начинается не с  "Bearer"
    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(header);
        if (Objects.isNull(bearerToken)) {
            return null;
        }

        if(bearerToken.startsWith("Bearer")){
            return bearerToken.substring("Bearer".getBytes().length);
        }

        throw new JwtAuthenticationException("Неверный токен авторизации - " + bearerToken);
    }

    public Authentication getAuthentication(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        String roleString = claims.get("role", String.class);
        Role role;
        if ("USER".equals(roleString)) {
            role = Role.USER;
        } else if ("ADMIN".equals(roleString)) {
            role = Role.ADMIN;
        } else {
            throw new JwtAuthenticationException("Ваш токен не действителен");
        }
        UserDetails userDetails = SecurityUser.fromToken(role, claims.getSubject(), claims.get("user_id",Integer.class));

        return new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
