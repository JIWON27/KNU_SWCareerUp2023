package com.example.blogAPI.web.dto;

import com.example.blogAPI.domain.blog.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponseDto {
  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;

  public ArticleViewResponseDto(Article article){
    this.id = article.getId();
    this.content = article.getTitle();
    this.title = article.getTitle();
    this.createdAt = article.getCreatedAt();
  }

}
