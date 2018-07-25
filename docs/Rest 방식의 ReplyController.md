# Rest 방식의 ReplyController
### REST 방식에서 자주 쓰이는 어노테이션
* @PathVariable : URI의 경로에서 원하는 데이터를 추출하는 용도로 사용합니다.
* @RequestBody : 전송된 JSON 데이터를 객체로 반환해주는 어노테이션으로 @ModelAttribute와 유사한 역할을 하지만,<br/>
JSON에서 사용한다는 것이 차이점입니다.

### Overloaded POST:<br/>브라우저에서 PUT, PATCH, DELETE 방식을 지원하기 위한 필터 추가
* 브라우저에 따라 GET과 POST 방식을 지원하고, PUT, PATCH, DELETE 방식은 지원하지 않을 경우가 있습니다.<br/>
해결책은 브라우저에서 POST 방식으로 전송하고, 추가적인 정보를 이용해 PUT, PATCH, DELETE와 같은 정보를 함께 전송하는 것입니다.
* 이것을 **Overloaded POST**라고 합니다.
* ```<form>```태그를 이용해 데이터를 전송할 때, POST 방식으로 전송하되 ```_method```라는 추가 정보를 이용합니다.
* 스프링은 이를 위해 ```HiddenHttpMethodFilter```라는 것을 제공합니다.
* ```<form>```태그 내에 ```<input type="hidden" name="_method" value="PUT">```와 같은 형태로 사용합니다.
* 이렇게 설정함으로써 GET, POST 방식만을 지원하는 브라우저에서 REST 방식을 사용할 수 있게 됩니다.
* 아래와 같이 ```web.xml```에 필터를 추가해주면 됩니다.

```
<filter>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>hiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```