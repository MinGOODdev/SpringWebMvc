<%--
  Created by IntelliJ IDEA.
  User: MinGOOD
  Date: 2018-07-26
  Time: 오후 7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%-- head --%>
<%@ include file="../include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

    <%-- Main Header --%>
    <%@ include file="../include/main_header.jsp" %>

    <%-- Left Side Column --%>
    <%@ include file="../include/left_column.jsp" %>

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
                <li class="active">AJAX</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">
            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">댓글 작성</h3>
                    </div>
                    <%-- 댓글 입력 영역 --%>
                    <div class="box-body">
                        <div class="form-group">
                            <label for="newReplyText">댓글 내용</label>
                            <input type="text" class="form-control" id="newReplyText" name="replyText"
                                   placeholder="댓글 내용을 입력해주세요.">
                        </div>
                        <div class="form-group">
                            <label for="newReplyWriter">댓글 작성자</label>
                            <input type="text" class="form-control" id="newReplyWriter" name="replyWriter"
                                   placeholder="댓글 내용을 입력해주세요.">
                        </div>
                        <div class="pull-right">
                            <button type="button" id="replyAddBtn" class="btn btn-primary"><i class="fa fa-save"></i> 댓글 저장</button>
                        </div>
                    </div>

                    <div class="box-footer">
                        <ul id="replies">
                            <%-- 댓글 목록 영역 --%>
                        </ul>
                    </div>

                    <div class="box-footer">
                        <div class="text-center">
                            <ul class="pagination pagination-sm no-margin">
                                <%-- 댓글 페이징 영역 --%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <%-- 댓글 수정/삭제를 위한 Modal 영역 --%>
            <div class="modal fade" id="modifyModal" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">댓글 수정창</h4>
                        </div>

                        <div class="modal-body">
                            <div class="form-group">
                                <label for="replyNo">댓글 번호</label>
                                <input type="text" class="form-control" id="replyNo" name="replyNo" readonly>
                            </div>
                            <div class="form-group">
                                <label for="replyText">댓글 내용</label>
                                <input type="text" class="form-control" id="replyText" name="replyText"
                                       placeholder="댓글 내용을 입력해주세요.">
                            </div>
                            <div class="form-group">
                                <label for="replyWriter">댓글 작성자</label>
                                <input type="text" class="form-control" id="replyWriter" name="replyWriter" readonly>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-success modalModBtn">수정</button>
                            <button type="button" class="btn-danger modalDelBtn">삭제</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%-- /.content --%>
    </div>
    <%-- /.content-wrapper --%>

    <%-- Main Footer --%>
    <%@ include file="../include/main_footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>

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

    // 댓글 저장 버튼 클릭 이벤트 발생시
    $("#replyAddBtn").on("click", function () {
        // 화면으로부터 입력 받은 변수값 처리
        var replyText = $("#newReplyText");
        var replyWriter = $("#newReplyWriter");

        var replyTextVal = replyText.val();
        var replyWriterVal = replyWriter.val();

        // AJAX 통신 : POST
        $.ajax({
            type : "post",
            url : "/replies",
            headers : {
                "Content-type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
            },
            dataType : "text",
            data : JSON.stringify({
                articleNo : articleNo,
                replyText : replyTextVal,
                replyWriter : replyWriterVal
            }),
            success : function (result) {
                if (result == "regSuccess") {
                    alert("댓글 등록 완료");
                }
                getReplies();           // 댓글 목록 출력 함수 호출
                replyText.val("");      // 댓글 내용 초기화
                replyWriter.val("");    // 댓글 작성자 초기화
            }
        });
    });
</script>

</body>
</html>