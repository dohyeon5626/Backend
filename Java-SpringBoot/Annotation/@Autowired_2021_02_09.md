# @Autowired

각 상황의 타입에 맞는 `IoC`컨테이너 안에 존재하는 `Bean`을 자동으로 주입해주는 어노테이션

```java
public class Cook{
	private String cooking;
	public Cook(){
		this.cooking = "조리중";
	}
    public void Fire(){
		System.out.println(cooking);
	}
}
```

```java
public class Restaurant{
	private Cook cook;
	public void Reserve(){
		cook.Fire();
	}
}
```

위 코드를 아래처럼 실행하게 된다면 오류가 생기게 됨

```java
Restaurant r1 = new Restaurant();
r1.Reserve();
```

왜냐하면 `Restaurant`가 `Cook`클래스에 의존되어 있어, `cook`를 먼저 만들고 실행해야 하므로 코드를 다음과 같이 바꿔야 함 (의존성 주입을 함)

```java
public class Restaurant{
	private Cook cook;
	public Restaurant(Cook cook){ // 이렇게 Cook을 넣어야 함
		this.cook = cook;
	}
	public void Reserve(){
		cook.Fire();
	}
}
```

그리고 그 다음

```java
Cook c1 = new Cook;
Restaurant r1 = new Restaurant(c1);
r1.Reserve();
```

이런식으로 많은 코드를 추가하고, 일일히 넣어줘야 함
그래서 이런 의존성 주입을 자동화 하는 것이 `@Autowired`이 해줌



