package com.yg.cm.jwt;

import com.yg.cm.dto.response.ResponseDto;
import com.yg.cm.dto.response.ResultCode;
import com.yg.cm.dto.response.ResultMessage;
import com.yg.cm.util.HttpUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpUtil.jsonResponse(ResponseDto.getResponse(HttpServletResponse.SC_UNAUTHORIZED,
                ResultCode.UNAUTHORIZED, ResultMessage.UNAUTHORIZED), response);
    }
}
