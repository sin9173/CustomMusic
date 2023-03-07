package com.yg.cm.config.auth;

import com.yg.cm.entity.member.Member;
import com.yg.cm.jwt.TokenProvider;
import com.yg.cm.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final MemberRepository memberRepository;

    private final TokenProvider tokenProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, TokenProvider tokenProvider) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.tokenProvider = tokenProvider;
    }

    // 권한이나 인증이 필요한 요청이 왔을 때 BasicAuthenticationFilter 가 동작
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(TokenProvider.AUTHORIZATION_HEADER);
        if(header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(TokenProvider.AUTHORIZATION_HEADER).replace("Bearer ", "");
        String user_id = tokenProvider.getSubject(token);

        if(user_id != null) {
            Member member = memberRepository.findAuthByUserId(user_id).get();
            PrincipalDetails principalDetails = new PrincipalDetails(member);

            //Authentication 객체를 생성
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, "1", principalDetails.getAuthorities());

            //Security Context 에 Authentication 을 넣어 인증
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
