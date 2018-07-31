<%--
  Created by IntelliJ IDEA.
  User: MinGOOD
  Date: 2018-07-25
  Time: 오후 7:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%-- head --%>
<%@ include file="../../include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

    <%-- Main Header --%>
    <%@ include file="../../include/main_header.jsp" %>

    <%-- Left Side Column --%>
    <%@ include file="../../include/left_column.jsp" %>

    <%-- Content Wrapper --%>
    <div class="content-wrapper">
        <%-- Content Header (Page header) --%>
        <section class="content-header">
            <h1>
                메인페이지
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-dashboard"></i> Main</a></li>
                <li class="active">Read Search</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">
            <p>Article FindOne(Pagination + Search)</p>
            <div class="col-lg-12">

                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">글제목 : ${article.title}</h3>
                    </div>
                    <div class="box-body" style="height: 700px">
                        ${article.content}
                    </div>
                    <div class="box-footer">
                        <div class="user-block">
                            <img class="img-circle img-bordered-sm" src="/dist/img/user1-128x128.jpg" alt="user image">
                            <span class="username"><a href="#">${article.writer}</a></span>
                            <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
                                                                      value="${article.regDate}"/></span>
                        </div>
                    </div>
                    <div class="box-footer">
                        <form role="form" method="post">
                            <input type="hidden" name="articleNo" value="${article.articleNo}"/>
                            <input type="hidden" name="page" value="${searchCriteria.page}"/>
                            <input type="hidden" name="perPageNum" value="${searchCriteria.perPageNum}"/>
                            <input type="hidden" name="searchType" value="${searchCriteria.searchType}"/>
                            <input type="hidden" name="keyword" value="${searchCriteria.keyword}"/>
                        </form>
                        <button type="submit" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
                        <div class="pull-right">
                            <button type="submit" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정</button>
                            <button type="submit" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제</button>
                        </div>
                    </div>
                </div>

                <%-- 댓글 입력 영역 --%>
                <div class="box box-warning">
                    <div class="box-header with-border">
                        <a class="link-black text-lg"><i class="fa fa-pencil"></i> 댓글 작성</a>
                    </div>
                    <div class="box-body">
                        <form class="form-horizontal">
                            <div class="form-group margin">
                                <div class="col-sm-10">
                                    <textarea name="newReplyText" id="newReplyText" rows="3" class="form-control"
                                              placeholder="댓글 내용을 입력하세요." style="resize: none"></textarea>
                                </div>
                                <div class="col-sm-2">
                                    <input type="text" id="newReplyWriter" name="newReplyWriter" class="form-control"
                                           placeholder="댓글 작성자를 입력해주세요.">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-primary btn-block replyAddBtn"><i
                                            class="fa fa-save"></i> 저장
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <%-- ./ 댓글 입력 영역 --%>

                <%-- 댓글 목록 & 페이징 영역 --%>
                <div class="box box-success collapsed-box">
                    <%-- 댓글 유무 & 댓글 개수 & 댓글 펼치기, 접기 --%>
                    <div class="box-header with-border">
                        <a href="" class="link-black text-lg"><i class="fa fa-comments-o margin-r-5 replyCount"></i></a>
                        <div class="box-tools">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-plus"></i>
                            </button>
                        </div>
                    </div>
                    <%-- 댓글 목록 --%>
                    <div class="box-body repliesDiv">

                    </div>
                    <%-- 댓글 페이징 --%>
                    <div class="box-footer">
                        <div class="text-center">
                            <ul class="pagination pagination-sm no-margin">

                            </ul>
                        </div>
                    </div>
                </div>
                <%-- ./ 댓글 목록 & 페이징 영역 --%>

                <%-- 댓글 수정 modal 영역 --%>
                <div class="modal fade" id="modModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">댓글 수정</h4>
                            </div>
                            <div class="modal-body" data-rno>
                                <input type="hidden" class="replyNo">
                                <textarea name="replyText" id="replyText" rows="3"
                                          class="form-control" style="resize: none"></textarea>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                                <button type="button" class="btn btn-primary modalModBtn">수정</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- ./ 댓글 수정 modal 영역 --%>

                <%-- 댓글 삭제 modal 영역 --%>
                <div class="modal fade" id="delModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">댓글 삭제</h4>
                                <input type="hidden" class="rno">
                            </div>
                            <div class="modal-body" data-rno>
                                <p>댓글을 삭제하시겠습니까?</p>
                                <input type="hidden" class="replyNo">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">아니오</button>
                                <button type="button" class="btn btn-primary modalDelBtn">예</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- ./ 댓글 삭제 modal 영역 --%>

            </div>
        </section>
        <%-- /.content --%>
    </div>
    <%-- /.content-wrapper --%>

    <%-- Main Footer --%>
    <%@ include file="../../include/main_footer.jsp" %>

</div>

<%@ include file="../../include/plugin_js.jsp" %>
<script>
    $(document).ready(function () {
        var articleNo = "${article.articleNo}";     // 현재 게시글 번호
        var replyPageNum = 1;                       // 댓글 페이지 번호 초기화

        // 댓글 내용 : 줄바꿈 / 공백 처리
        Handlebars.registerHelper("escape", function (replyText) {
            var text = Handlebars.Utils.escapeExpression(replyText);
            text = text.replace(/(\r\n|\n|\r)/gm, "<br/>");
            text = text.replace(/( )/gm, "&nbsp;");
            return new Handlebars.SafeString(text);
        });

        // 댓글 등록일자 : 날짜 / 시간
        Handlebars.registerHelper("prettifyDate", function (timeValue) {
            var dateObj = new Date(timeValue);
            var year = dateObj.getFullYear();
            var month = dateObj.getMonth() + 1;
            var date = dateObj.getDate();
            var hours = dateObj.getHours();
            var minutes = dateObj.getMinutes();

            // 2자리 숫자로 변환
            month < 10 ? month = '0' + month : month;
            date < 10 ? date = '0' + date : date;
            hours < 10 ? hours = '0' + hours : hours;
            minutes < 10 ? minutes = '0' + minutes : minutes;
            return year + "-" + month + "-" + date + " " + hours + ":" + minutes;
        });

        // 댓글 목록 함수 호출
        getRepliesPaging("/replies/" + articleNo + "/" + replyPageNum);

        function getRepliesPaging(repliesUri) {
            $.getJSON(repliesUri, function (data) {
                printReplyCount(data.pageMaker.totalCount);
                printReplies(data.replies, $(".repliesDiv"), $("#replyTemplate"));
                printReplyPaging(data.pageMaker, $(".pagination"));
            });
        }

        // 댓글 갯수 출력 함수
        function printReplyCount(totalCount) {
            var replyCount = $(".replyCount");
            var collapsedBox = $(".collapsed-box");

            // 댓글이 없을 경우
            if (totalCount === 0) {
                replyCount.html(" 댓글이 없습니다. 의견을 남겨주세요.");
                collapsedBox.find(".btn-box-tool").remove();
                return;
            }

            // 댓글이 존재하는 경우
            replyCount.html(" 댓글 목록 (" + totalCount + ")");
            collapsedBox.find(".box-tools").html(
                "<button type='button' class='btn btn-box-tool' data-widget='collapse'>"
                + "<i class='fa fa-plus'></i>"
                + "</button>"
            );
        }

        // 댓글 목록 출력 함수
        function printReplies(replyArr, targetArea, templateObj) {
            var replyTemplate = Handlebars.compile(templateObj.html());
            var html = replyTemplate(replyArr);
            $(".replyDiv").remove();
            targetArea.html(html);
        }

        // 댓글 페이징 출력 함수
        function printReplyPaging(pageMaker, targetArea) {
            var str = "";
            if (pageMaker.prev) {
                str += "<li><a href='" + (pageMaker.startPage - 1) + "'>이전</a></li>";
            }
            for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; ++i) {
                var strClass = pageMaker.criteria.page == i ? "class=active" : "";
                str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
            }
            if (pageMaker.next) {
                str += "<li><a href='" + (pageMaker.endPage + 1) + "'>다음</a></li>";
            }
            targetArea.html(str);
        }

        // 댓글 페이지 번호 클릭 이벤트
        $(".pagination").on("click", "li a", function (event) {
            event.preventDefault();
            replyPageNum = $(this).attr("href");
            getRepliesPaging("/replies/" + articleNo + "/" + replyPageNum);
        });

        // 댓글 저장 버튼 클릭 이벤트
        $(".replyAddBtn").on("click", function () {
            // 입력 form 선택자
            var replyWriterObj = $("#newReplyWriter");
            var replyTextObj = $("#newReplyText");
            var replyWriter = replyWriterObj.val();
            var replyText = replyTextObj.val();

            // 댓글 입력 처리 수행
            $.ajax({
                type : "post",
                url : "/replies/",
                headers : {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "POST"
                },
                dataType : "text",
                data : JSON.stringify({
                    articleNo : articleNo,
                    replyWriter : replyWriter,
                    replyText : replyText
                }),
                success: function (result) {
                    console.log("result : " + result);
                    if (result === "regSuccess") {
                        alert("댓글이 등록되었습니다.");
                        replyPageNum = 1;       // 페이지를 1로 초기화
                        getRepliesPaging("/replies/" + articleNo + "/" + replyPageNum);
                        replyTextObj.val("");   // 댓글 입력창 공백 처리
                        replyWriterObj.val("");
                    }
                }
            });
        });

        // 댓글 수정을 위한 modal에 해당 댓글의 값 불러오기
        $(".repliesDiv").on("click", ".replyDiv", function (event) {
            var reply = $(this);
            $(".replyNo").val(reply.attr("data-replyNo"));
            $("#replyText").val(reply.find(".oldReplyText").text());
        });

        // modal의 댓글 수정 버튼 클릭 이벤트
        $(".modalModBtn").on("click", function () {
            var replyNo = $(".replyNo").val();
            var replyText = $("#replyText").val();

            $.ajax({
                type : "put",
                url : "/replies/" + replyNo,
                headers : {
                    "Content-type" : "application/json",
                    "X-HTTP-Method-Override" : "PUT"
                },
                dataType : "text",
                data : JSON.stringify({
                    replyText : replyText
                }),
                success : function (result) {
                    console.log("result : " + result);
                    if (result === "modifySuccess") {
                        alert("댓글이 수정되었습니다.");
                        getRepliesPaging("/replies/" + articleNo + "/" + replyPageNum);
                        $("#modModal").modal("hide");   // modal close
                    }
                }
            });
        });

        // modal의 댓글 삭제 버튼 클릭 이벤트
        $(".modalDelBtn").on("click", function () {
            var replyNo = $(".replyNo").val();

            $.ajax({
                type : "delete",
                url : "/replies/" + replyNo,
                headers : {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "DELETE"
                },
                dataType : "text",
                success : function (result) {
                    console.log("result : " + result);
                    if (result === "deleteSuccess") {
                        alert("댓글이 삭제되었습니다.");
                        getRepliesPaging("/replies/" + articleNo + "/" + replyPageNum);
                        $("#delModal").modal("hide");   // modal close
                    }
                }
            });
        });



        var formObj = $("form[role='form']");
        console.log(formObj);

        $(".modBtn").on("click", function () {
            formObj.attr("action", "/article/paging/search/modify");
            formObj.attr("method", "get");
            formObj.submit();
        });

        $(".delBtn").on("click", function () {
            var replyCnt = $(".replyDiv").length;
            if (replyCnt > 0) {
                alert("댓글이 달린 게시글은 삭제할수 없습니다.");
                return;
            }
            formObj.attr("action", "/article/paging/search/remove");
            formObj.submit();
        });

        $(".listBtn").on("click", function () {
            formObj.attr("action", "/article/paging/search/list");
            formObj.attr("method", "get");
            formObj.submit();
        });
    });
</script>

<%--
댓글 목록 (하나의 댓글을 구성하는 템플릿 코드)
Handlerbars의 {{#each.}}{{/each}}은 JSTL의 <c:forEach>처럼 배열의 루프를 처리하기 위해 사용
prettifyDate와 escape은 Handlerbars의 확장 기능을 사용한 것으로 JS 코드에서 처리
--%>
<script id="replyTemplate" type="text/x-handlersbars-template">
    {{#each.}}
    <div class="post replyDiv" data-replyNo={{replyNo}}>
        <div class="user-block">
            <img src="/dist/img/user1-128x128.jpg" alt="user image" class="img-circle img-bordered-sm">
            <span class="username">
                <a href="#">{{replyWriter}}</a>
                <a href="#" class="pull-right btn-box-tool replyDelBtn" data-toggle="modal" data-target="#delModal">
                    <i class="fa fa-times"> 삭제</i>
                </a>
                <a href="#" class="pull-right btn-box-tool replyModBtn" data-toggle="modal" data-target="#modModal">
                    <i class="fa fa-edit"> 수정</i>
                </a>
            </span>
            <span class="description">{{prettifyDate regDate}}</span>
        </div>
        <div class="oldReplyText">{{{escape replyText}}}</div>
        <br/>
    </div>
    {{/each}}
</script>

</body>
</html>
