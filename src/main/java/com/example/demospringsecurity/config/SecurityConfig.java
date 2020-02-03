package com.example.demospringsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests() // HttpServletRequest 을 사용하여 접근제한 설정 가능
                .mvcMatchers("/","/info").permitAll()  // "/" , "/info" 요청은 모두 허용한다.
                .mvcMatchers("/admin").hasRole("ADMIN") // "/admin" 요청은 "ADMIN" 권한을 가지고 있어야 허용한다
                .anyRequest().authenticated(); // 그 외의 요청은 권한이 있기만 하면 허용한다.

        http.formLogin(); // Security에서 기본적으로 제공하는 Login폼 사용
        http.httpBasic(); // HTTP 기본 인증을 구성
    }


}
