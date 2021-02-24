# @RequiredArgsConstructor

`final`이나 `@NonNull`인 필드 값만 파라미터로 받는 생성자를 추가하는 어노테이션

```java
public class Hello{
    private final int A;
    private String B;
}
```

만약 위와 같은 클래스가 있을때

```java
@RequiredArgsConstructor
public class Hello{
    private final int A;
    private String B;
}
```

이렇게 `@RequiredArgsConstructor`어노테이션을 붙이게 된다면 A와 같이 final인 필드 값만 파라미터로 받는 생성자를 추가하게 되므로, 아래와 같은 코드처럼 작동하게 됨

```java
public class Hello{
    private final int A;
    private String B;
    public Hello(int A){
       	this.A = A;
    }
}
```

