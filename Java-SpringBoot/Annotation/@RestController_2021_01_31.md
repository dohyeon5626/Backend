# @RestController

= `@Controller` + `@ResponseBody`라고 생각하면 됨

![img](https://blog.kakaocdn.net/dn/7bceC/btqx8K6BbxE/LVs4KK74mUj9CZ70uHTsjK/img.png)

`@Controller`는 기본적으로 view를 반환하지만, `@RestController`는 객체(VO,DTO)를 반환하기만 하면, 객체데이터는 application/json 형식의 HTTP ResponseBody에 직접 작성되게 됨

```java
@RestController
public class testController {
    @RequestMapping(value = "/test")
    public String home(){
        return "test HelloWorld";
    }
}
```

위와 같이 코드를 짜게 되면, 뷰가 아니라, 데이터가 반환이 되기 때문에 다음과 같이 뜨게 됨

![image-20210131001232571](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20210131001232571.png)

```java
@ResponseBody
@Controller
public class testController {
    @RequestMapping(value = "/test")
    public String home(){
        return "test HelloWorld";
    }
}
```

위와 같이 `@ResponseBody`, `@Controller`를 써도 같은 결과가 나옴