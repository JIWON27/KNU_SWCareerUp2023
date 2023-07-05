package com.example.blogAPI.config.jwt;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.blogAPI.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {
  // 주요 기능 : 토큰 생성 및 들어온 토큰 검증
  private final JwtProperties jwtProperties;

  // token 생성 메서드
  public String generateToken(User user, Duration expiredAt){
    Date now = new Date(); //token 생성 시작

    return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
  }

  // token을 실제로 생성하는 메서드
  public String makeToken(Date expiry, User user){
    Date now = new Date();

    // 토큰 객체 생성
    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 타입은 JWT
        .setIssuer(jwtProperties.getIssuer())
        .setIssuedAt(now) // 기준시각 즉 현재시각
        .setExpiration(expiry)
        .setSubject(user.getEmail())
        .claim("id", user.getId())
        .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret()) //d어떤 알고리즘 기법으로 서명할래?
        .compact(); // 왜 build()가 아니냐? 그냥..스프링 시큐리티에서는 compact()로 지정했대..
  }

  // token의 유효성 검사
  public boolean validToken(String token) {
    try{
      // 복호화를 위한 파싱 작업
      Jwts.parser()
          .setSigningKey(jwtProperties.getSecret())
          .parseClaimsJws(token);
      return true;

    }catch(Exception e){
      return false;
    }
  }
  // 토큰 기반으로 인증저보를 가져오는 것
  // 토큰 값을 가지고 holder안에 있는 인증 객체를 생성해야함
  public Authentication getAuthentication(String token){
    Claims claims = getClaims(token);
    // Authentication 안에 pricipal, credential, authorities 정보를 가지고 있음.

    // 권한은 아직 안만들었으니 지금은 후딱 땜빵용으로 땜빵함
    Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

    return new UsernamePasswordAuthenticationToken(
        new org.springframework.security.core.userdetails.User(
            claims.getSubject(), "",authorities
        ), // principal
        token, //credential 확인차 넣은 것
        authorities //authorities
    );
  }

  public Long getUserId(String token){
    // 토큰에 해당하는 유저 아이디 반환
    Claims claims = getClaims(token);
    return claims.get("id", Long.class);
  }

  private Claims getClaims(String token){
    return Jwts.parser()
        .setSigningKey(jwtProperties.getSecret())
        .parseClaimsJws(token)
        .getBody(); //바디에 해당하는 클레임즈들 넘어가도록
  }

  // 이 메서드의 실행시점은 언제인가?
  // TokenAuthenticaionFilter
}
