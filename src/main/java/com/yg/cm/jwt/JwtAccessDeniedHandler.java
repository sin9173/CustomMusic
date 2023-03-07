package com.yg.cm.jwt;

import com.yg.cm.dto.response.ResponseDto;
import com.yg.cm.dto.response.ResultCode;
import com.yg.cm.dto.response.ResultMessage;
import com.yg.cm.util.HttpUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpUtil.jsonResponse(ResponseDto.getResponse(HttpServletResponse.SC_FORBIDDEN,
                ResultCode.FORBIDDEN, ResultMessage.FORBIDDEN), response);
    }
}
