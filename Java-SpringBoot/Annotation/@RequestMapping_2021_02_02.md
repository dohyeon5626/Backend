# @RequestMapping

요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
메서드 뿐만이 아니라 Class에도 적용할 수 있음

### 속성

| **이름** | **타입**       | **설명**                                                     |
| -------- | -------------- | ------------------------------------------------------------ |
| value    | String[]       | URL 값으로 매핑 조건을 부여 (default)                        |
| method   | RequetMethod[] | HTTP Request 메소드 값을 매핑 조건으로 부여 사용 가능한 메소드는 GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE (7개) |
| params   | String[]       | HTTP Request 파라미터를 매핑 조건으로 부여                   |
| consumes | String[]       | 설정과 Content-Type request 헤더가 일치할 경우에만 URL이 호출됨 |
| produces | String[]       | 설정과 Accept request 헤더가 일치할 경우에만 URL이 호출됨    |

> value = { "Value 1" , "Value 2"}로 작성을 하면 URL Value1, Value2 둘다 접근이 가능함

### Ex)

```java
@RestController
public class testController {
    @RequestMapping(value = "/test")
    public String home(){
        return "test HelloWorld";
    }
}
```

위코드를 친 다음 `http://localhost:8080/test`링크로 가면 다음과 같이 매핑이 된 것을 알 수 있음

![image-20210202012451501](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20210202012451501.png)

# @PostMapping, @GetMapping, @PutMapping, @DeleteMapping, @PatchMapping

Spring 4.3부터 위와 같은 새로운 어노테이션이 추가되었음

이름에서 알 수 있듯이 
@PostMapping은 Post로
@GetMapping은 Get으로
@PutMapping은 Put으로
@DeleteMapping은 Delete로
@PatchMapping은 Patch로 맵핑되는 것을 알 수 있음

```java
@RequestMapping(value = "/hello", method = { RequestMethod.POST })
```

위와 같은 코드를 아래와 같이 간편하게 쓸 수 있음

```java
@PostMapping("/hello")
```

그래서 다음과 같은 코드를 쓴다면 코드 수를 줄일 수 있고, 보기에도 어떤 HttpMethod로 매핑시킬지 알기 쉬움