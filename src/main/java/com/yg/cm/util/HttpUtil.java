package com.yg.cm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yg.cm.dto.response.ResponseDto;
import com.yg.cm.jwt.TokenProvider;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
    public static <T> void jsonResponse(T data, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new ObjectMapper().writeValueAsString(ResponseDto.getResponseDto(data));
        response.getWriter().write(json);
    }

    public static <T> void jsonErrorResponse(Integer status, Integer resultCode, String message, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new ObjectMapper().writeValueAsString(ResponseDto.getResponseDto(status, resultCode, message));
        System.out.println("json : " + json);
        response.getWriter().write(json);
        response.setStatus(status);
    }

    public static void refreshTokenCookie(String token, HttpServletResponse response, int time) {
        Cookie cookie = new Cookie("refresh_token", token);
        cookie.setMaxAge(time);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public static String getCookieParam(String s) {
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();

        String result = null;
        String key;
        String value;

        if(cookies == null) return null;
        for(Cookie c : cookies) {
            key = c.getName();
            value = c.getValue();
            if(key.equals(s)) {
                result = value;
                break;
            }
        }

        return result;
    }
}
