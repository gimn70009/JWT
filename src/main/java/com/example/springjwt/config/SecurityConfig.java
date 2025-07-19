package com.example.springjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 클래스가 스프링 부트한테 Configuration 클래스로 관리하기 위함
@EnableWebSecurity // 이 클래스는 Security를 위한 Config이기 때문
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { // 암호화

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable (세션 방식에서는 세션이 항상 고정되기 때문에 csrf 공격이 필수적으로 방어를 해줘야 한다. 하지만 jwt 방식은 csrf에 대한 공격을 방어하지 않아도 된다)
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //경로별 인가 작업 (AdminController, MainController라는 특정한 경로에 대해서 어떤 권한을 가져야 하는지에 대한 인가 작업)
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll() // 해당 경로에 대해서는 모든 권한을 허용
                        .requestMatchers("/admin").hasRole("ADMIN") // /admin 경로는 ADMIN 권한을 가진 사용자만 접근 가능
                        .anyRequest().authenticated()); // 이외의 다른 요청에 대해서는 로그인 한 사용자만 접근 가능

        //세션 설정 (중요함. 꼭 STATELESS 상태로 만들어줘야 한다)
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
