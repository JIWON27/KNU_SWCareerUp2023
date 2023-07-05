package com.example.blogAPI.domain.blog;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED) // 디폴트 생성자를 바로 대체해주는 롬복 어노테이션, access는 보안성을 높이기 위한 것
@EntityListeners(AuditingEntityListener.class) // 시간 자동으로 수정해주는거 같음... 찾아보기
@Entity
public class Article {
  // 롬복쓰면 게터새터 ,생성자 관련 설정 등 이러한 단순 작업 코드들을 어노테이션으로 처리할 수 있는 플로그인임
  // 클래스단, 메서드단에 롬복이 붙을 수 있음
  // 클래스에 @Getter를 주면 자동으로 생성해줌!
  // 롬복 어노테이션들을 맨 위에, 스프링 관련 어노테이션들을 롬복 아래에 씀
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrease, 자동 증가된다는거같음.
  @Column(name = "id", updatable = false) //db에서 어떤 컬럼 이름으로 쓰일지 ()안에 지정해줌, updatable은 데이터 수정여부, id는 수정 불가능
  private Long id;

  @Column(name = "title", nullable = false) //게시글 제목이 없으면 안되니 Null값은 불가라는 것을 의미.
  private String title;

  @Column(name ="content", nullable = false)
  private String content;

  @CreatedDate // 이런건 왜 별도의 어노테이션이 있느냐? 모든 테이블에는 등록 시각 ,수정 시각은 다 있다고 생각하면 된다. 자주 쓰는것들을 만들어 놓은거같음
  @Column(name = "created_at") // 왜 카멜문법을 쓰지 않고 언더바를 사용하느냐? 디비에서는 _ 붙이는게 관례임
  private LocalDateTime createdAt;//생성 시간
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt; //수정 시간

  @Builder
  public Article(String title, String content) {
    // Id는 AutoIncrease이고 시각도 어노테이션 자동 생성 줫으니 안받음
    this.title = title;
    this.content = content;
  }
  // update하는 기능을 명시힌다.
  public void update(String title, String content){
    this.title = title;
    this.content = content;
  }

}
