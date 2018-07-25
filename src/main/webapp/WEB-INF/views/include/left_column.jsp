<%--
  Created by IntelliJ IDEA.
  User: MinGOOD
  Date: 2018-06-29
  Time: 오전 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--left_column.jsp--%>
<%-- Left side column. contains the logo and sidebar --%>
<aside class="main-sidebar">

    <%-- sidebar: style can be found in sidebar.less --%>
    <section class="sidebar">

        <%-- Sidebar user panel (optional) --%>
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/dist/img/avatar5.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>Guest</p>
                <%-- Status --%>
                <a href="#"><i class="fa fa-circle text-success"></i> offline</a>
            </div>
        </div>

        <%-- search form (Optional) --%>
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="게시글 검색">
                <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
            </div>
        </form>
        <%-- /.search form --%>

        <%-- Sidebar Menu --%>
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">메뉴</li>
            <%-- Optionally, you can add icons to the links --%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>게시판(기본)</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/article/write"><i class="fa fa-pencil"></i> 게시글 작성</a></li>
                    <li><a href="${path}/article/list"><i class="fa fa-list"></i>게시글 목록</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>게시판(페이징)</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/article/write"><i class="fa fa-pencil"></i> 게시글 작성</a></li>
                    <li><a href="${path}/article/listPaging"><i class="fa fa-list"></i>게시글 목록</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>게시판(페이징+검색)</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/article/write"><i class="fa fa-pencil"></i> 게시글 작성</a></li>
                    <li><a href="${path}/article/paging/search/list"><i class="fa fa-list"></i>게시글 목록</a></li>
                </ul>
            </li>
        </ul>
        <%-- /.sidebar-menu --%>
    </section>
    <%-- /.sidebar --%>
</aside>