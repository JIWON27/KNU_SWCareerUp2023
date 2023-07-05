package com.example.blogAPI.web;

import com.example.blogAPI.service.UserService;
import com.example.blogAPI.web.dto.AddUserRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UserApiController {

  private final UserService userService;

  @PostMapping("/user")
  public String signUp(AddUserRequestDto addUserRequestDto) {
    // 호원가입
    userService.save(addUserRequestDto);
    // 로그인 성공시 다음에 가야할 화면
    return "redirect:/login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response){
    // Spring security에 설정된 값들을 삭제해줘야함.
    // 즉, holder에 저장된 사용자 값들을 제거해주는 코드임. -> 로그아웃 해주는 코드
    new SecurityContextLogoutHandler().logout(
        request,
        response,
        SecurityContextHolder.getContext().getAuthentication()
    );
    // 로그아웃이 잘되면 로그인 화면으로 돌아도록 해줌.
    return "redirect:/login";
  }
}
