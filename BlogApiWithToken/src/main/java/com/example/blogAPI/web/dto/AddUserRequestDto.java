package com.example.blogAPI.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequestDto {
  private String email;
  private String password;
}
