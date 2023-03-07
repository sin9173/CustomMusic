package com.yg.cm.jwt;

import com.yg.cm.dto.auth.AuthSuccessDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Getter
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token-millis}")
    private long tokenMillis;

    @Value("${jwt.token-refresh-millis}")
    private long tokenRefreshMillis;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private Key key;
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public AuthSuccessDto createToken(Authentication authentication) { //JWT 토큰 생성
        String access_token = createAccessToken(authentication);
        String refresh_token = createRefreshToken();
        return new AuthSuccessDto(access_token, refresh_token);
    }

    public String createAccessToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName()) //Subject 에 회원아이디
                .setExpiration(new Date(System.currentTimeMillis() + (this.tokenMillis)))
                .signWith(key)
                .compact();
    }

    public String createRefreshToken() {
        return Jwts.builder()
                .setSubject("")
                .setExpiration(new Date(System.currentTimeMillis() + (this.tokenRefreshMillis)))
                .signWith(key)
                .compact();
    }

    public String getSubject(String token) { //JWT 토큰 파싱
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();

        //PrincipalDetails principle = new PrincipalDetails(); //Authentication 에 들어갈 UserDetails 를 생성

        //return new UsernamePasswordAuthenticationToken(principle, token, authorities); //UserDetails 로 Authentication 객체를 생성
    }
}
