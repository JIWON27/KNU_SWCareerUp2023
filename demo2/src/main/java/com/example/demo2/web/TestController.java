package com.example.demo2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping ("/api/latest") //여러개의 컨트롤러가 있을꺼아니야? 그거 구분해주기위해서 달아준 어노테이션 같음 1차 매핑, 디스패처 서블릿이 여기로 가~ 라고 알려주는 1차 매핑하는 곳
public class TestController {
  //Create
  @PostMapping("/test") //2차 매핑, http 프로토콜 메서드 정리 고고 어느정고 규약이라 외우고 있는게 좋긴함.
  // 경로 : https://localhost:8080/api/latest/test
  //8080은 스프링부트 기본 포트 번호
  //@RequestBody은 요청에서 들어온 정보임을 확인하기 위한 어노테이션 -> 맞는지 확인하기
  public ResponseEntity<String> create(@RequestBody Map<String, String> map) {
    // 회원 정보가 담겨서 요청이 올꺼임 -> map
    // map에 담겨오는 파라미터는 val1, val2라고 전제
    System.out.println("map.val1 = " + map.get("val1"));
    System.out.println("map.val2 = " + map.get("val2"));

    //반환타입 맞춰줌
    return ResponseEntity.ok("CREATED");
  }

  //Read - 단건 조회 (test컨트롤러니까 test를 적어준 것)
  @GetMapping("/test/{id}") // id를 파라미터로  받아 해당 회원을 조회하는 것
  // 경로 : https://localhost:8080/api/latest/test/1
  // // 경로 : https://localhost:8080/api/latest/test/2
  // @PathVariable을 가져오기 위해서 사용한 어노테이션
  public ResponseEntity<String> read(@PathVariable Long id){
    System.out.println("id = " + id);

    return ResponseEntity.ok("Hello World KNU");
  }
  //Update
  @PutMapping("/test/{id}") // 파라미터로 받은 id의 회원을 업데이트
  // 경로 : https://localhost:8080/api/latest/test/1
  // 경로 : https://localhost:8080/api/latest/test/2
  // 수정할 것이니 어떤 내용으로 수정할 것인지를 알아야함. 그래서 파라미터가 더 붙음
  public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Map<String, String> map){
    System.out.println("id = " + id);
    System.out.println("map.val1 = " + map.get("val1"));
    System.out.println("map.val2 = " + map.get("val1"));

    return ResponseEntity.ok("UPDATED");
  }
  //Delete
  @DeleteMapping("/test/{id}")
  // 경로 : https://localhost:8080/api/latest/test/1
  // 경로 : https://localhost:8080/api/latest/test/2
  public ResponseEntity<String> delete(@PathVariable Long id){
    System.out.println("id = " + id);
    return ResponseEntity.ok("DELETED");
  }
}