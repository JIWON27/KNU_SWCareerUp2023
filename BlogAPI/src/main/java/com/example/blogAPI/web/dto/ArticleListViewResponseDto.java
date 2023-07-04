package com.example.blogAPI.web.dto;

import com.example.blogAPI.domain.blog.Article;
import lombok.Getter;


@Getter
public class ArticleListViewResponseDto {
  private final Long id;
  private final String title;
  private final String content;
  // entity -> dto
  public ArticleListViewResponseDto(Article article){
    this.id = article.getId();
    this.title = article.getTitle();
    this.content = article.getContent();
  }

}
