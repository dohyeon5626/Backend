# @ResponseBody

자바 객체를 HTTP 요청의 body 내용으로 매핑하는 어노테이션

> @RequestBody는 HTTP 요청의 body 내용을 자바 객체로 매핑하는 역할음 하니, 반대의 개념이라 생각하면 됨

> @ResponseBody + @Controller가 @RestController가 됨

```java
@Controller
public class HelloWorld{
    public String Hello(){ // 이 메서드는 뷰가 반환됨
        return "Hello";
    }
    @ResponseBody
    public String World(){ // 이 메서드는 데이터가 반환됨
        return "World";
    }
}
```

