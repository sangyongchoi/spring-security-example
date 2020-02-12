package com.example.demospringsecurity.config;

import com.example.demospringsecurity.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests() // HttpServletRequest 을 사용하여 접근제한 설정 가능
                .mvcMatchers("/", "/info","/account/**","/login**","/logoutForm").permitAll()  // "/" , "/info" 요청은 모두 허용한다.
                .mvcMatchers("/admin").hasRole("ADMIN") // "/admin" 요청은 "ADMIN" 권한을 가지고 있어야 허용한다
                .mvcMatchers("/user").hasRole("USER")
                .anyRequest().authenticated(); // 그 외의 요청은 권한이 있기만 하면 허용한다.

        http.formLogin()
                .loginPage("/loginForm")
                .permitAll();
        http.httpBasic(); // HTTP 기본 인증을 구성
        http.logout()
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
