# @CrossOrigin

**CORS** 보안상의 문제로 브라우저에서 리소스를 현재 origin에서 다른 곳으로의 AJAX요청을 방지하는 것

> CORS : Cross-Origin Resource Sharing, 서로 다른 도메인 출처 Resource(css, image, javascript 등)에 있어 접근가능 및 실행 권한

```java
@CrossOrigin(origins = "~~~~~")
```

> origins : 허용할 도메인을 나타냄

[get 통신 실험](https://github.com/dohyeon5626/TIL/tree/master/%EC%8B%A4%ED%97%98%EC%8B%A4/get%20%ED%86%B5%EC%8B%A0%20%EC%8B%A4%ED%97%98_2021_02_01)에서는 index.html 파일과 서버과 같은 사이트므로 CORS 문제가 없어 `@CrossOrigin`을 붙일 필요가 없음

하지만, 만약 index.html이 서버와 같은 사이트가 아닌, 따로 분리가 되어 요청을 하게 된다면, CORS문제가 생김
그런 경우에는 서로 공유를 설정해야 함