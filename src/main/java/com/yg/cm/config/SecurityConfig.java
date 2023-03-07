package com.yg.cm.config;


import com.yg.cm.config.auth.JwtAuthenticationFilter;
import com.yg.cm.config.auth.JwtAuthorizationFilter;
import com.yg.cm.filter.JwtExceptionHandlerFilter;
import com.yg.cm.jwt.JwtAccessDeniedHandler;
import com.yg.cm.jwt.JwtAuthenticationEntryPoint;
import com.yg.cm.jwt.TokenProvider;
import com.yg.cm.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final TokenProvider tokenProvider;

    private final MemberRepository memberRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf().disable(); //CSRF 토큰을 사용하지 않음

        http.addFilterBefore(new JwtExceptionHandlerFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않음
                .and()
                .formLogin().disable() //로그인시 로그인창을 사용하지 않음
                .httpBasic().disable()

                .addFilter(new JwtAuthenticationFilter(authenticationManager(), tokenProvider, redisTemplate))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository, tokenProvider))

                .authorizeRequests()
                .antMatchers("/profile/**").access("hasRole('ROLE_USER')")
                .anyRequest().permitAll(); //나머지 요청을 허용

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
