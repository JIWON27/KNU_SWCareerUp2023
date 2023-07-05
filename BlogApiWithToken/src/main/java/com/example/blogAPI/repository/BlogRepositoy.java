package com.example.blogAPI.repository;

import com.example.blogAPI.domain.blog.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepositoy extends JpaRepository<Article,Long> {
  // H2 db 사용할꺼니 JPA 사용할꺼라고 알려줘야함
  // CRUD를 JPA가 자동으로 가지고있어서 굳이 생성안해도됨.
  // 구현체가 필요 없음!
  // Jpa, 엔티디, dto, 각종 어노태이션들 정리....
}
