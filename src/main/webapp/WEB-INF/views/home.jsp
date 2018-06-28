<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%-- head --%>
<%@ include file="include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%-- Main Header --%>
    <%@ include file="include/main_header.jsp" %>

    <%-- Left Side Column --%>
    <%@ include file="include/left_column.jsp" %>

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
            <p>메인 페이지입니다.</p>
            <%--------------------------
              | Your Page Content Here |
              --------------------------%>
        </section>
        <%-- /.content --%>
    </div>
    <%-- /.content-wrapper --%>

    <%-- Main Footer --%>
    <%@ include file="include/main_footer.jsp" %>

</div>

<%@ include file="include/plugin_js.jsp" %>

</body>
</html>