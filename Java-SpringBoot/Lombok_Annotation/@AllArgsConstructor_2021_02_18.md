# @AllArgsConstructor

모든 필드 값을 파라미터로 받는 생성자를 추가해주는 어노테이션

```java
public class Hello{
    private String A;
    private String B;
}
```

위와 같이 있는 코드에 `@AllArgsConstructor`를 붙여보면

```java
@AllArgsConstructor
public class Hello{
    private String A;
    private String B;
}
```

위 코드가 아래 코드와 같이 모든 필드 값을 포함하는 생성자를 추가하는 것을 알 수 있음

```java
public class Hello{
    private String A;
    private String B;
    public Hello(String A, String B){
        this.A = A;
        this.B = B;
    }
}
```

> 파라미터의 순서는 클래스에 있는 필드 순서에 맞춰서 생성함
> 이 어노테이션도 AccessLevel을 이용해 접근제한자를 설정 할 수 있음
>
> ```java
> @AllArgsConstructor(access = AccessLevel.PROTECTED)
> ```
>
> 