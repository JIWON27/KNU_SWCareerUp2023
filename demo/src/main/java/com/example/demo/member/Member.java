package com.example.demo.member;

import com.example.demo.DemoApplication;

// 도메인 단위의 Entity
public class Member {
  // 역할 : 아이디(long), 이름(string), 등급(Grade)
  // 클래스 : 필드 + 메서드
  private Long id;
  private String name;
  private Grade grade;

  public Member(Long id, String name, Grade grade) {
    this.id = id;
    this.name = name;
    this.grade = grade;
  }

  /*public String toString(){

  }*/

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Grade getGrade() {
    return grade;
  }

  public void setId(long id) {
    this.id = id;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setGrade(Grade grade){
    this.grade = grade;
  }
}
