package com.yg.cm.filter;

import com.yg.cm.dto.response.ResponseDto;
import com.yg.cm.dto.response.ResultCode;
import com.yg.cm.dto.response.ResultMessage;
import com.yg.cm.util.HttpUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtExceptionHandlerFilter extends OncePerRequestFilter {


    // Spring Security 에서 동작할 ExceptionHandler 필터
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String message = null;
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            message = ResultMessage.TOKEN_INVALID;
        } catch (UnsupportedJwtException e) {
            message = ResultMessage.TOKEN_INVALID;
        } finally {
            if(StringUtils.hasText(message)) {
                HttpUtil.jsonErrorResponse(HttpServletResponse.SC_UNAUTHORIZED,
                        ResultCode.TOKEN_INVALID, ResultMessage.TOKEN_INVALID, response);
            }
        }
    }
}
