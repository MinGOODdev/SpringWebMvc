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

---

### JS 코드를 이용한 댓글 목록 가져오기
Case 1
```
<script>
    var articleNo = 1;
    $.getJSON("/replies/all/" + articleNo, function (data) {
        console.log(data);
    });
</script>
```
* jQuery를 이용하여 특정 게시글(1번째 게시글)의 댓글 목록을 배열 형태로 가져옵니다.
* ```@RestController```의 경우 객체를 JSON 방식으로 전달하기 때문에 getJSON()을 아래와 같이 사용합니다.

---

Case 2
```
<script>
    var articleNo = 1;
    getReplies();
    
    function getReplies() {
        $.getJSON("/replies/all/" + articleNo, function (data) {
            console.log(data);

            var str = "";
            $(data).each(function () {
                str += "<li data-replyNo='" + this.replyNo + "' class='replyLi'>"
                    + "<p class='replyText'>" + this.replyText + "</p>"
                    + "<p class='replyWriter'>" + this.replyWriter + "</p>"
                    + "<button type='button' class='btn btn-xs btn-success' data-toggle='modal' data-target='#modifyModal'>댓글 수정</button>"
                    + "</li>"
                    + "<hr/>";
            });
            $("#replies").html(str);
        })
    }
</script>
```
* ```<li>```태그 속성에 **data-replyNo** 와 같이 **data-** 로 시작되는 속성은 이름이나 그 개수에 관계 없이<br/>
id나 name 속성을 대신해서 사용하기에 편리합니다.
* 이 속성을 통해 댓글 수정/삭제 처리시 댓글 번호를 설정합니다.