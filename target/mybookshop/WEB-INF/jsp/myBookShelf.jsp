<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>我的书架</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/myBookshelf.css">
</head>
<body>
<!-- 导航条 -->
<nav class="navbar">
    <div class="nav-menu">
        <ul class="menu">
            <li><a class="active" href="home.do">首页</a></li>
            <li><a href="goBookStore.do">书籍良品</a></li>
            <li><a href="goAskBookStore.do">求书区</a></li>
            <li><a href="#">服务区</a></li>
        </ul>
    </div><!-- nav-menu -->

    <div class="nav-search">
        <form action="searchBook.do" method="post">
            <input type="search" class="searchIn" name="name" placeholder="搜图书...">
            <button class="search-logo"><img src="<%=request.getContextPath()%>/img/search2.png"></button>
        </form>
    </div>

    <div class="nav-info">
        <a href="#" class="username">${user.getName()}</a>
        <a href="myBookshelf.do" class="bookshelf">||&nbsp;&nbsp;&nbsp;我的书架</a>
        <a href="logout.do" class="logout">[ 退 出 ]</a>
    </div> <!-- nav-info-end -->
</nav>

<!-- 个人信息 -->
<div class="person-info">
    <p>
        <a href="#" class="name">${user.getName()}</a>
    </p>
    <input type="hidden" id="sexInput" value="${user.getSex()}">
    <p>
        <a href="#" class="sex" id="sexText">女</a><img src="<%=request.getContextPath()%>/img/girl.png" id="sex-pic">
    </p>
    <p class="set">
        <a href="#" class="edit">编辑个人主页</a>
        <img class="set-icon" src="<%=request.getContextPath()%>/img/setting.png">
    </p>
    <div class="contact">
        <p>
            <span class="call-icon"></span>
            联&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系：
            <a href="#" class="num">${user.getTel()}</a></p>
        <p>
            <span class="address-icon"></span>
            住&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：<a href="#"  class="address">${user.getAddress()}</a>
        </p>
        <p>
            <span class="major-icon"></span>
            年级专业：<a  href="#" class="major">${user.getMajor()}</a>
        </p>
    </div>
</div><!-- person-info-end -->

<!-- 书摊和求书选项卡 -->
<div class="card-btn">
    <span title="sell-book" id="sell-btn" class="active">我的书摊</span>
    <span title="ask-book" id="ask-btn">我的求书</span>
    <p class="upload-icon"></p>
    <a id="sell-upload" href="goUpload.do?bookType=1">上传书至书摊</a>
    <a id="ask-upload" href="goUpload.do?bookType=0">上传求书信息</a>
    <a id="delete-book" href="#">删除二手书</a>
</div>

<!-- 我的书摊 -->
<div id="container">
    <div id="sell-book">
        <ul class="book-ul">
            <c:forEach items="${books}" var="book" varStatus="bookStatus">
            <li class="book-list">
                <p class="book-pic">
                    <img src="img/book-list/article/${book.getBookImage().getId()}.jpg">
                </p>
                <h5 class="book-name">${book.getName()}</h5>
                <form>
                    <input type="text" name="book-id" class="book-id" value="${book.getId()}">
                </form>
                <span class="delete-btn"></span>
                <a href="goEditBook.do?id=${book.getId()}" class="edit-btn"></a>
                <div class="book-detail">
                    <p>原&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：<span class="orinal-price">￥${book.getOriginalPrice()}</span></p>
                    <p>售&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：<span class="book-price">￥${book.getPrice()}</span></p>
                    <p>品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;相：<span class="book-condition">${book.getDegree()}新</span></p>
                    <p>商品描述：<span class="book-desc">${book.getDescription()}</span></p>
                </div>
            </li>
            </c:forEach>
        </ul>
    </div> <!-- sell-book-end -->

    <!-- 我的求书 -->
    <div id="ask-book">
        <ul class="book-ul">
            <c:forEach items="${askBooks}" var="askBook" varStatus="askBookStatus">
            <li class="book-list">
                <p class="book-pic">
                    <img src="img/book-list/article/${askBook.getBookImage().getId()}.jpg">
                </p>
                <h5 class="book-name">${askBook.getName()}</h5>
                <a class="edit-btn" href="goEditAskBook.do?id=${book.getId()}"></a>
                <div class="book-detail">
                    <p>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：<span class="book-author">${askBook.getAuthor()}</span></p>
                    <p>出&nbsp; 版&nbsp;社：<span class="book-press">${askBook.getPress()}</span></p>
                </div>
            </li>
            </c:forEach>
        </ul>
    </div><!-- ask-book-end-->
</div> <!-- container-end -->

<footer>
    <a href="#">©2018-2019 二手书交易</a>
    <a href="#">意见反馈&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;隐私权声明&nbsp;&nbsp;&nbsp;使用条款</a>
</footer>

<!-- javascript- -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myBookshelf.js"></script>
</body>
</html>
