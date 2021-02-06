# @RequestParam

단일 파라미터를 전달 받을 때 사용하는 어노테이션

```java
@RestController
public class GetController {
    @GetMapping("/get")
    public String get_return (@RequestParam(value = "test", defaultValue = "현재 서버로 올라온 값이 없음") String text){
        return text;
    }
}
```

저렇게 컨트롤러를 만든 후 `http://localhost:8080/get`에 get 요청을 하면 `@RequestParam`으로 값을 전달 받을 수 있음

`http://localhost:8080/get` -> "현재 서버로 올라온 값이 없음" 반환
`http://localhost:8080/get?test=hello` -> "hello" 반환

> URL 뒤에 `?[value]=[보낼 값]` 이렇게 붙이면 get 요청을 할 수 있음
> (get 요청은 URL에 정보를 붙여서 보냄)

### 주의사항

`defaultValue`가 설정되어 있지 않은 파라미터에 값이 안 들어온다면 404에러가 발생하니 주의해야 함

---

위와 같이 작업을 하게된다면 일일이 파라미터를 지정하는게 귀찮아 짐
이 작업을 Map으로 간단하게 받아올 수 있음

```java
@RestController
public class GetController {
    @GetMapping("/get")
    public String get_return (@RequestParam HashMap<string,string> paramMap){
        return paramMap.get("test");
    }
}
```

위와 같이 작업해도 똑같은 결과가 나옴
다만 다른 점은 값을 전송하지 않아도 404에러가 생기지는 않음 (그냥 값이 안 들어감)

> HashMap<K,V>
>
> K : 키 타입
> V : 갑 타입

