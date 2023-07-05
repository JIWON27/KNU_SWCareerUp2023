package com.example.blogAPI.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticaionFilter extends OncePerRequestFilter {

  private final TokenProvider tokenProvider; //유효성 확인하는게 필터 앞단에서 동작해야하니까 호출
  private final static String HEADER_AUTHORIZATION = "Authorization"; //여기서 헤더는 http의 헤더를 말함
  private final static String TOKEN_PREFIX = "Bearer "; // "Authorization"의 valie값, Bearer + token값으로 주어질 것임.

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // 요청 헤더의 Authorization 키 값을 조회하는 로직
    String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
    // 가져온 값에서 token값만 추출하기 위한 접두사 제거
    String token = getAccessToken(authorizationHeader);
    // 추출한 토큰값이 유효한지 확인하고, 유효하다면 인증정보를 만든다.
    if (tokenProvider.validToken(token)){
      Authentication authentication = tokenProvider.getAuthentication(token);
      // holder에 집어넣어야함
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    // 필터한테 성공했다고 알려줘야함
    filterChain.doFilter(request, response);
  }

  private String getAccessToken(String authorizationHeader){
    if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
      return authorizationHeader.substring(TOKEN_PREFIX.length());
    }
    return null;
  }
}
