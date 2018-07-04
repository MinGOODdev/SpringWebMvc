<%--
  Created by IntelliJ IDEA.
  User: MinGOOD
  Date: 2018-07-04
  Time: 오후 5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%-- head --%>
<%@ include file="../include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini">
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
                <li><a href="#"><i class="fa fa-dashboard"></i> Main</a></li>
                <li class="active">Home</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">
            <p>Article List</p>
            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 목록</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th style="width: 30px">#</th>
                                <th>제목</th>
                                <th style="width: 100px">작성자</th>
                                <th style="width: 150px">작성시간</th>
                                <th style="width: 60px">조회</th>
                            </tr>
                            <c:forEach items="${articles}" var="article">
                                <tr>
                                    <td>${article.articleNo}</td>
                                    <td>
                                        <a href="${path}/article/read?articleNo=${article.articleNo}">${article.title}</a>
                                    </td>
                                    <td>${article.writer}</td>
                                    <td><fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd a HH:mm"/></td>
                                    <td><span class="badge bg-red">${article.viewCnt}</span></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-footer">
                        <div class="text-center">
                            <ul class="pagination">
                                <c:if test="${pageMaker.prev}">
                                    <li><a href="${path}/article/listPaging?page=${pageMaker.startPage - 1}">이전</a></li>
                                </c:if>
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="index">
                                    <li <c:out value="${pageMaker.criteria.page == index ? 'class=active' : ''}"/> >
                                        <a href="${path}/article/listPaging?page=${index}">${index}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <li><a href="${path}/article/listPaging?page=${pageMaker.endPage + 1}">다음</a></li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                    <div class="box-footer">
                        <div class="pull-right">
                            <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                                <i class="fa fa-pencil"></i> 글쓰기
                            </button>
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
    var result = "${msg}";
    if (result == "regSuccess") {
        alert("게시글 등록이 완료되었습니다.");
    }
    else if (result == "modifySuccess") {
        alert("게시글 수정이 완료되었습니다.");
    }
    else if (result == "deleteSuccess") {
        alert("게시글 삭제가 완료되었습니다.");
    }
</script>

</body>
</html>

