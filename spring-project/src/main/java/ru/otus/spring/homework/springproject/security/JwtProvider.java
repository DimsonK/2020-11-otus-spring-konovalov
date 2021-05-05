package ru.otus.spring.homework.springproject.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework.springproject.config.AppProperties;
import ru.otus.spring.homework.springproject.models.entity.Role;
import ru.otus.spring.homework.springproject.models.entity.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider {

    private final AppProperties properties;

    public JwtProvider(AppProperties properties) {
        this.properties = properties;
    }

    public String generateToken(User user) {
        Date iat = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date exp = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var jwtPayload = new JwtPayload(
            user.getId().toString(), user.getUsername(), user.getEmail(),
            user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()),
            UUID.randomUUID().toString(), iat, exp);
        ObjectMapper mapper = new ObjectMapper();
        String payload = "";
        try {
            payload = mapper.writeValueAsString(jwtPayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Jwts.builder()
            .setPayload(payload)
            .signWith(SignatureAlgorithm.HS512, properties.getJwtSecret())
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(properties.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.warn("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.warn("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.warn("Malformed jwt");
        } catch (SignatureException sEx) {
            log.warn("Invalid signature");
        } catch (Exception e) {
            log.warn("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(properties.getJwtSecret()).parseClaimsJws(token).getBody();
        return (String) claims.get("username");
    }

}
