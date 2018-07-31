# Javascript 활용

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

---

### JS 댓글 수정 GET
```
$("#replies").on("click", ".replyLi button", function () {
        var reply = $(this).parent();

        var replyNo = reply.attr("data-replyNo");
        var replyText = reply.find(".replyText").text();
        var replyWriter = reply.find(".replyWriter").text();

        $("#replyNo").val(replyNo);
        $("#replyText").val(replyText);
        $("#replyWriter").val(replyWriter);
    });
```
* 클릭 이벤트 선택자가 ```<ul>```의 id인 ```replies```로 되어 있습니다.
* 단순히 클릭이 발생한 수정 버튼을 선택자로 지정하면 될 것 같지만 AJAX 통신 후에 생기는 요소이기 때문에 이벤트 처리가 되지 않습니다.
그래서 AJAX 통신 이전에 존재하는 ```<ul>```을 이용하여 이벤트를 등록해야 합니다.

---

### Handlebars를 이용한 JS 템플릿 적용
자바스크립트 템플릿을 활용하면 위 예제들과 같이 str 번수에 HTML 코드를 계속 붙여가면서 작성해야 했던 것을 좀 더 깔끔하게 코딩할 수 있습니다.
자바스크립트 템플릿 종류에는 JS Render, Mustache, Mustache를 기반으로 한 Handlerbars, Hogans 등이 있습니다.
* [Handlebars의 구체적인 사용법](https://handlebarsjs.com)