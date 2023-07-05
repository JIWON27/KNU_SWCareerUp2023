package com.example.blogAPI.web;

import com.example.blogAPI.domain.blog.Article;
import com.example.blogAPI.service.BlogService;
import com.example.blogAPI.web.dto.ArticleListViewResponseDto;
import com.example.blogAPI.web.dto.ArticleResponseDto;
import com.example.blogAPI.web.dto.ArticleViewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller //Requestbody 필요없기 때문에 그냥 컨트롤러씀
public class BlogViewController {

  private final BlogService blogService;
  // view 템플릿은 String을 반환 타입으로 가지는게 특징
  // 모든 게시글을 보여주는 메서드
  @GetMapping("/articles")
  public String getArticles(Model model){
    List<ArticleListViewResponseDto> articles = blogService.findAll()
        .stream()
        .map(ArticleListViewResponseDto::new)
        .collect(Collectors.toList());
    model.addAttribute("articles", articles); //여기서 에트리뷰트들은 dto에 담긴 데이터들이라고 생각

    return "articleList"; // 실제로 열릴 페이지의 이름
  }

  // 특정 게시물 조회
  // 보통 화면단은 다 get으로 통신한다고 생각
  // 모델에 담아서 반환
  // Model : Controller에서 생성된 데이터를 담아서 View로 전달할 때 사용하는 객체
  @GetMapping("/articles/{id}")
  public String getArticle(@PathVariable Long id, Model model){
    Article article = blogService.findById(id);
    // entity타입을 바로 화면ㅇ단에 보여줄 수 없으니 dto로 변환해주고 화면에 띄워야함.
    model.addAttribute("article", new ArticleViewResponseDto(article));

    return "article";
  }

  // 신규 글 작성과 수정을 동시에 처리하는 메서드
  // 즉, 수정할땐 id값을 받고 글 작성은 id값 없어야함, -> required = false 로 해결
  // 값이 없으면 null임
  @GetMapping("/new-article")
  public String newArticle(@RequestParam(required = false) Long id, Model model){
    if (id == null){
      // 글 신규등록
      model.addAttribute("article", new ArticleViewResponseDto());
    }else{
      // 업데이트
      Article article = blogService.findById(id);
      model.addAttribute("article", new ArticleViewResponseDto(article));
    }

    return "newArticle";
  }
  // 뷰 화면은 리소스 밑 템플릿
}
