# @RequestBody

**@RequestBody** 어노테이션을 사용하여 HTTP 요청 본문에 담긴 값들을 자바 객체로 변환시켜, 객체에 저장

> 클라이언트에서 서버로 필요한 데이터를 전송하기 위해서 JSON이라는 데이터를 요청 본문에 담아서 서버로 보냄

그렇기에 `get`요청을 `@RequestParam`어노테이션을 이용해 받는 것과 달리 `post`와 같은 본문에 값을 담아서 통신하는 통신방식에서는 `@RequestBody`를 씀

```java
@PostMapping("/post")
public Post post_return (@RequestBody Param param){
	return new Post(counter.incrementAndGet(), param.getData());
}
```

### Cf

만약 받는 파라미터의 타입이 String으로 문자열이었으면, 문자열로 오기 때문에 데이터를 사용하기에 불편함
그래서 새로운 클래스를 생성하여, 각 변수별로 데이터를 저장할 수 있음 (위에 예시처럼)