package com.example.blogAPI.web.dto;

import com.example.blogAPI.domain.blog.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor // 매개변수 있는 생성자
@Getter // 왜 세터는 안받을까? 데이터를 변조하지 못하게? 하는거 같음.. 꼭 변조가 필요한 부분에만 사용하는게 관례.
public class AddArticleRequestDto {

  private String title;
  private String content;

  // dto 핵심 코드
  public Article toEntity() {
    // dto타고 request가 넘겨오고 article로 컨버팅해서 반환, add~dto가 db에 저장될려면 article로 변환되어야하니까 그 부분임
    // 엔티티는 오로지 디비와 통신할때만 사용하는거임.
    return Article.builder()
        .title(title)
        .content(content)
        .build();
  }

}
