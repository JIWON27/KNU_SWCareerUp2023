package com.example.blogAPI.config;

import com.example.blogAPI.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

  private  final UserDetailService userDetailService;

  // 스프링 시큐리티 기능 비활성화 부분
  @Bean
  public WebSecurityCustomizer configure() {
    return (web) -> web.ignoring()
        .requestMatchers(toH2Console())  // H2 데이터베이스 콘솔에 대한 요청을 무시
        .antMatchers("/static/**"); //static의 하위의 모든 부분에 대한 요청 무시
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    return http
        .authorizeRequests() //인증과 인가를 설정하겠다.
        .antMatchers("/login","/signup", "/user").permitAll() //여기 3개의 주소는 다 접근하게 하겠다. 시큐리티가 필요없는 부분
        .anyRequest().authenticated() // 그 외의 나머지 모든애들
        // 인증 인가 설정 끝

        .and() // 인증 인가 이외에 또다른 설정
        .formLogin() // 폼 로그인 인증 기반
        .loginPage("/login")
        .defaultSuccessUrl("/articles") //로그인 성공시 처음 볼 화면

        .and()
        .logout() // 로그아웃 설정
        .logoutSuccessUrl("/login") //로그아웃시 처음 볼 화면
        .invalidateHttpSession(true) // 일단 넘어감. 어려움

        .and()
        .csrf().disable() // 기본으로 활성화되어있어서 꺼줘야함.
        .build();
  }
  // 인증 관리자 관련 설정
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http,
                                                     BCryptPasswordEncoder bCryptPasswordEncoder,
                                                     UserDetailService userDetailService) throws Exception {
    // http.getSharedObject(AuthenticationManagerBuilder.class)를 통해
    // 공유겍체인 AuthenticationManagerBuilder 가져와서 AuthenticationManager를 설정한다.
    return http
        .getSharedObject(AuthenticationManagerBuilder.class)
        //userDetailsService(userDetailService)는 인증 처리에 사용될 UserDetailsService를 설정
        .userDetailsService(userDetailService)
        // 비밀번호에는 암호화 방식에는 bCryptPasswordEncoder로 설정
        .passwordEncoder(bCryptPasswordEncoder)
        .and()
        .build(); // AuthenticationManager를 최종적으로 생성하여 반환
  }

  // 패스워드 인코더로 사용할 빈 등록, BCryptPasswordEncoder를 사용해서 암호화를 하겠다
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
