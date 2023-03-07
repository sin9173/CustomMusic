package com.yg.cm.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yg.cm.dto.auth.AuthSuccessDto;
import com.yg.cm.dto.auth.MemberAuthDto;
import com.yg.cm.dto.response.ResponseDto;
import com.yg.cm.jwt.TokenProvider;
import com.yg.cm.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


//로그인 요청시 동작
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    private final RedisTemplate<String, Object> redisTemplate;

    // 로그인 요청 시도 시 실행
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        MemberAuthDto memberAuthDto = null;
        try {
            memberAuthDto = objectMapper.readValue(request.getInputStream(), MemberAuthDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberAuthDto.getUsername(), memberAuthDto.getPassword());

        // PrincipalService 에서 override 한 loadUserByUsername 이 실행
        return authenticationManager.authenticate(authenticationToken);
    }

    //attemptAuthentication 에서 정상적으로 인증이 되었을 경우 successAuthentication 이 실행
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthSuccessDto tokenDto = tokenProvider.createToken(authResult);

        //Redis 에 Refresh 토큰을 저장
        redisTemplate.opsForValue()
                        .set("RefreshToken:" + authResult.getName(), tokenDto.getRefresh_token(),
                                tokenProvider.getTokenRefreshMillis(), TimeUnit.MILLISECONDS);

        // Access 토큰을 헤더에, Refresh 토큰을 쿠키에 넣어서 응답
        response.addHeader(TokenProvider.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getAccess_token());
        
        HttpUtil.refreshTokenCookie(tokenDto.getRefresh_token(), response, (int) tokenProvider.getTokenRefreshMillis());
        HttpUtil.jsonResponse(tokenDto, response);
    }
}
