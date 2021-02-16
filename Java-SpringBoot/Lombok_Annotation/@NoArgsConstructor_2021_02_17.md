# @NoArgsConstructor

파라미터가 없는 기본 생성자를 생성해주는 어노테이션

> 기본 생성자는 매개변수를 하나도 가지지 않고, 아무런 명령어도 포함하지 않음
>
> ```java
> 클래스이름() {}
> ```

기본 생성자의 경우에는 자동으로 생성되지만, 만약 다른 생성자가 존재하면, 자동으로 생성하지 않기 때문에 기본 생성자를 생성하기 위해서는 직접 생성해야 함

그렇기에 직접 생성할 경우 `@NoArgsConstructor`를 이용해 간단히 생성할 수 있음

```java
public class User {
    private String name;
    private Long age;
    public User(String name, Long age){...} // 생성자가 있으므로 기본생성자가 자동으로 생성 안됨
}
```

위 코드에 `@NoArgsConstructor`를 붙여보면

```java
@NoArgsConstructor
public class User {
    private String name;
    private Long age;
    public User(String name, Long age){...}
}
```

위 코드가 아래처럼 작동하게 됨

```java
public class User {
    private String name;
    private Long age;
    public User(){}
    public User(String name, Long age){...}
}
```

### 기타

추가로 `public`이 아니라 `protected`와 같은 접근제한자도 다음과 같이 설정할 수 있음

```java
@NoargsConstructor(AccessLevel.PROTECTED) // protected로 접근 제어
```



