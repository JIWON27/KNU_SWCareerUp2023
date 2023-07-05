package com.example.blogAPI.web;

import com.example.blogAPI.domain.blog.Article;
import com.example.blogAPI.service.BlogService;
import com.example.blogAPI.web.dto.AddArticleRequestDto;
import com.example.blogAPI.web.dto.ArticleResponseDto;
import com.example.blogAPI.web.dto.UpdateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

  private final BlogService blogService;

  @PostMapping("/api/latest/articles")
  public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequestDto requestDto) {
    // RequestBody는 서블릿으로부터 담겨오는 매핑객체임을 알게해주는 어노테이션
    // create는 status붙임
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(blogService.save(requestDto));

  }
  // 전체 조회
  @GetMapping("/api/latest/articles")
  public ResponseEntity<List<ArticleResponseDto>> findAllArticles() {
    // blogService.findAll(); -> 여기서 리턴되는가 Article 리스트니 이걸 responsedto로 변환해줘야함.
    List<ArticleResponseDto> articles = blogService.findAll()
        .stream()
        .map(ArticleResponseDto::new) //ArticleResponseDto로 새로 생성할꺼야 라는 뜻
        .collect(Collectors.toList());

    return ResponseEntity.ok().body(articles);
  }
  // 단건 조회
  @GetMapping("/api/latest/articles/{id}")
  public ResponseEntity<ArticleResponseDto> findArticle(@PathVariable Long id){
    return  ResponseEntity.ok()
        .body(new ArticleResponseDto(blogService.findById(id)));

  }
  // 삭제
  @DeleteMapping("/api/latest/articles/{id}")
  public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
    // 원래는 <id>를 적는걸 추천하는데 void형식도 있다는 것을 알려주기 위해서 사용
    blogService.delete(id);
    return ResponseEntity.ok().build();
  }

  // 수정
  @PutMapping("/api/latest/articles/{id}")
  public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                               @RequestBody UpdateArticleRequestDto requestDto){
    Article updatedArticle = blogService.update(id, requestDto);

    return ResponseEntity.ok().body(updatedArticle);
  }

}
