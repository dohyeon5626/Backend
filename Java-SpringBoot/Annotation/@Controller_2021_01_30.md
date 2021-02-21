# @Controller

컨트롤러 클래스에 @Controller 어노테이션을 작성
해당 어노테이션이 적용된 클래스는 bean으로 등록되며 해당 클래스가 Controller로 사용됨을 Spring Framework에 알려줌

전통적인 Spring MVC의 컨트롤러임
주로 View를 반환하기 위해 사용함

### view 반환

![img](https://blog.kakaocdn.net/dn/2BnED/btqybg36Dak/3HgL3gUKHBSOmyeM4hIn00/img.png)

```java
@Controller
public class testController {
    @RequestMapping("/test")
    public String home(){
        return "test.html"; //view가 test.html
    }
}
```

참고로 `void`형일떄는 자동으로 생성되는 View 이름이 사용됨
URL과 view 이름을 통일하여 사용해야 함

하지만, view만이 아니라 데이터를 반환해야 하는 경우도 있음

### Data 반환

![img](https://blog.kakaocdn.net/dn/bEJ1YG/btqx8Tvu8qa/lkDg8cu2G4xMi8Pg22C1f0/img.png)

만약 데이터를 반환해야 하는 경우가 생길시에는 `@ResponseBody` 어노테이션을 활용해 주어야 함
이를 통해 Controller도 Json 형태로 데이터를 반환할 수 있음

```java
@ResponseBody
@Controller
public class testController {
    @RequestMapping("/test")
    public String home(){
        return "test.html"; // "test.html"이라는 Data가 반환됨
    }
}
```

위와 같이 실행하면

![image-20210130014953871](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20210130014953871.png)

이렇게 view가 뜨지 않음

### 기타

추가적으로 다시 url을 매핑하고 싶은 순간이 생길 수 있음, 그 경우는 redirect를 이용하여 매핑함

```java
@RequestMapping("test")
	public class test{
	@RequestMapping("hello")
	public String hello(){
    	return "redirect:world"; // world라는 링크로 매핑이 됨
	}
	@RequestMapping("world") // 여기로 매핑이 됨
	public String world(){ ... }
}
```

