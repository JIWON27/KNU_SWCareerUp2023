package com.example.blog.web.dto;

import com.example.blog.domain.blog.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {

  private String title;
  private String content;

  // 디비에 있는 엔티티를 가지고 dto로 반들어야함
  public ArticleResponseDto(Article article){
    this.title = article.getTitle();
    this.content = article.getContent();
  }
}
