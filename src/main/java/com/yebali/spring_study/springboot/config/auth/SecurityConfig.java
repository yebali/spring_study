package com.yebali.spring_study.springboot.config.auth;

import com.yebali.spring_study.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() // url별 권한 관리의 시작점, 선언되어야만 antMatchers 사용 가능
                    .antMatchers("/", "/css/**", "/image/**", "/js/**", "h2-console/**").permitAll() // 모든 열람 허용
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // User권한만 허용
                    .anyRequest().authenticated() //나머지 url들은 모두 인증된(login) 사용자들에게만 허용
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService); // login성공 시 후속 조치를 진행할 UserService 인터페이스 구현체를 등록
    }
}
