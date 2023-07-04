package com.example.blogAPI.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name="email", nullable = false, unique = true)
  private String email;

  @Column(name = "password")
  private String password;

  @Builder
  public User(String email, String password, String auth){
    this.email = email;
    this.password = password;
  }

  // UserDetails<<interface>>에서 원하는 오버라이딩 메서드들
  @Override
  // 권한을 반환하는 메서드
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("user"));
  }

  @Override
  // 사용자의 아이디 반환하는 메서드, 여기서는 이메일값 반환
  public String getUsername() {
    return email;
  }

  @Override
  // 계정이 만료되었는지에 대한 여부 반환
  public boolean isAccountNonExpired() {
    // 만료되었는지를 확인하는 로직이어서 true는 만료되지 않은 것
    return true;
  }

  @Override
  // 계정 잠김 여부 반환
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  // 비밀번호 만료 여부 반환
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  // 계정 사용 가능 여부 반환
  public boolean isEnabled() {
    return true;
  }
}
