<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${book.getName()}</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bookDetail.css">
</head>
<body>
<nav class="navbar">
    <div class="nav-menu">
        <ul class="menu">
            <li><a class="active" href="/home.do">首页</a></li>
            <li><a href="/goBookStore.do">书籍良品</a></li>
            <li><a href="/goAskBookStore.do">求书区</a></li>
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
        <a href="/myBookshelf.do" class="bookshelf">||&nbsp;&nbsp;&nbsp;我的书架</a>
        <a href="#" class="logout">[ 退 出 ]</a>
    </div> <!-- nav-info-end -->
</nav>

<div class="container">
    <div id="book-pic">
        <div class="book-big">
            <img src="<%=request.getContextPath()%>/img/book-list/article/${book.getBookImage().getId()}.jpg">
        </div>
        <span class="tip"></span>
    </div> <!-- book-pic-end -->

    <div id="book-info">
        <span class="book-name">${book.getName()}</span>
        <span class="book-publish">${book.getAuthor()} 著 / ${book.getPress()} / ${book.getPublishDate()} / ${book.getVersion()}</span>
        <span class="book-price">
				<p>售价 <a class="final-price">￥${book.getPrice()}</a></p>
				<p>定价 <a class="original-price">￥${book.getOriginalPrice()} </a></p>
				<p>品相 <a class="condition">${book.getDegree()}新</a></p>
			</span>
        <span class="book-descr">
				<p>商品描述<a class="book-desc">${book.getDescription()}</a></p>
				<p>上书时间<a class="upload-time">2018-03-20</a></p>
			</span>
        <span class="buy-now">立即联系卖家进行购买</span>
    </div> <!-- book-info-end -->

    <div id="seller-info">
        <a class="seller-name">${book.getUser().getName()}</a>
        <span class="seller-span1">
				联系<a class="seller-chat">在线联系</a>
			</span>
        <span class="seller-span2">
				<p>电话<a class="seller-num">${book.getUser().getTel()}</a></p>
				<p>宿舍<a class="seller-adr">${book.getUser().getAddress()}</a></p>
				<p>好评率<a class="seller-appraise">90.5%</a></p>
			</span>
        <span class="seller-span3">
				<p>[ 去看看卖家的二手书 ]</p>
			</span>
    </div><!-- seller-info-end -->
    <div id="book-content">
        <span class="book-tip">目送共由七十四篇散文组成，是为一本极具亲情、感人至深的文集。由父亲的逝世、母亲的苍老、儿子的离开、朋友的牵挂、兄弟的携手共行，写出失败和脆弱、失落和放手，写出缠绵不舍和绝然的虚无。正如作者所说：“我慢慢地、慢慢地了解到，所谓父女母子一场，只不过意味着，你和他的缘分就是今生今世不断地在目送他的背影。</span>
    </div>
</div><!--  container -->
<footer>
    <a href="#">©2018-2019 二手书交易</a>
    <a href="#">意见反馈&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;隐私权声明&nbsp;&nbsp;&nbsp;使用条款</a>
</footer>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/session.js"></script>
</body>
</html>
