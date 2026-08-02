* RequestBody, ResponseBody(요청, 응답 처리)
- 실제로 위 어노테이션이 어떤 역할을 하는지 알아보자.

- @RequestBody : HTTP 요청의 body에 담긴 JSON 문자열을 자바 객체로 변환한다.
                 HttpMessageConverter가 변환을 담당한다.
- @ResponseBOdy : 메서드가 반환한 자바 객체를 JSON으로 변환하여 body에 담는다.
                  @RestController=@Controller+@ResponseBody라서,
                  클래스에 @RestController가 붙어있다면, 모든 메서드에 @ResponseBody가 자동 적용된다.

* ResponseEntity 
- ResponseEntity.ok(body)	            200	  조회,  수정 성공
- ResponseEntity.status(201).body(data)	201	  생성(POST) 성공 - "새로 만들어졌다"는 의미를 명확히
- ResponseEntity.noContent().build()	204	  삭제 성공, 보여줄 데이터 없음
- ResponseEntity.badRequest().body(msg)	400	  클라이언트 요청 자체가 잘못됨
- ResponseEntity.notFound().build()	    404	  리소스를 못 찾음