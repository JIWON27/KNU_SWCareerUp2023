package com.example.blogAPI.service;

import com.example.blogAPI.domain.blog.Article;
import com.example.blogAPI.repository.BlogRepositoy;
import com.example.blogAPI.web.dto.AddArticleRequestDto;
import com.example.blogAPI.web.dto.UpdateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor// 롬복의 final이 붙은거 자동으러 처리해주는 어노테이션
@Service
public class BlogService {

  private final BlogRepositoy blogRepositoy;

  // save
  public Article save(AddArticleRequestDto requestDto){
    // requestDto 형태로 디비에 저장이 안되니 toEntity()로 변환해서 디비에 save 해야함.
    return blogRepositoy.save(requestDto.toEntity());
  }
  // Read
  // 1. id를 받아 단건조회
  // 조회도 트랜잭션 붙일 수 있는데 속도를 빠르게 하기 위해서 ()안에 readOnly를 붙여줄 수 있음.
  public Article findById(Long id){
    return blogRepositoy.findById(id)
        .orElseThrow( () -> new IllegalArgumentException("article not exist! : " + id));
  }
  // 2. 전체 게시물 다 조회
  public List<Article> findAll(){
    return blogRepositoy.findAll(); // jPa 자동 생성... 너무 많은 게시물이 있을땐 10개씩 끊어와 이렇게 끊어서 할 수 있긴함.
    // 여기선 페이지 처리 안함
  }

  // U
  // jpa 영속성을 알고있어야 수정을 작성할 수 있음
  @Transactional
  // 기존 게시글을 수정하는게 메인 기능인데 작업이 순서가 존재함.
  // 이렇게 서비스 레이어에서 처리의 순서들을 명세하는 역할이 큼 (기능은 엔티티에 명시했음)
  // 이러한 step1,step2가 다 동작되어야 update가 된다고 햇으니 이런것을 하나의 세트로 묶어 처리하기 위해 @Transactional을 붙임.
  public Article update(Long id, UpdateArticleRequestDto requestDto) {
    // step 1 . 기존 등록된 글 가져오기
    Article article = blogRepositoy.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("article not exist! : " + id));

    // 지금 이 메서드가 업데이트하는 메서드이므로,,, 서비스 단에서 조회된 데이터를
    // 업데이트 하는 코드가 나오면 된다. -> article entity에서 처리함.
    // 서비스 레이어에서 하는 역할은 작업의 순서와 흐름을 정의하는 용도로 사용한다.

    // step 2. 원한는 제목과 본문 내용으로 수정
    article.update(requestDto.getTitle(), requestDto.getContent());
    // db로 넘겨야할꺼같은데 리포지토리로 넘기는 무언가가 없다.............
    // 바로 리턴으로 넘겼는데,, 동작이 됨. 왜냐하면 JPA의 특성때문임..
    // article이라는 객체는 디비에서 조회한 객체잖아? 그 객체에 업데이트를 했으니, 영속성 컨텍스트 3번 특징에 의해서
    // 반영하는 코드가 없어도 특정 시점에 자동으로 반영이 될것임.
    return article;

  }
  // D
  public void delete(Long id){
    blogRepositoy.deleteById(id);
  }

}
