# @PathVariable

`BaseUrl/index?index=hello`와 같은 형식은 @RequestParam 어노테이션을 사용하여 hello라는 값을 받아옴
하지만, `BaseUrl/index/hello`와 같은 형식에서 hello라는 값을 받아올때는 @PathVariable 어노테이션을 사용함

```java
@GetMapping("hello/{idx}")
@ResponseBody
public String postDeleteFactory(@PathVariable("idx") String factoryIdx) {
	return factoryIdx;
}
```

이렇게 코드가 짜여지게 된다면, `hello/world`라고 url에 접근을 했을때 `factoryIdx`에는 world라는 값이 들어가게 되면서, 값을 받아올 수 있음

> 실제로 사용을 할때는 @RequestParam만 쓰거나, @PathVariable만 쓰지는 않고 복합적으로 사용함