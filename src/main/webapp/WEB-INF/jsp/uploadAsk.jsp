<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>上传二手书至书摊</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/upload.css">
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
<div class="titleName">
    <h3>上传求书信息</h3>
    <p>设求书信息</p>
</div>

<div class="ask container">
    <form enctype="multipart/form-data" id="bookForm" class="upload-form">
        <p>
            <span class="pic-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            上传书图片： <input type="file" name="image">
        </p>
        <input type="hidden" name="bookType" value="0">
        <p>
            <span class="name-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="name">
        </p>
        <p>
            <span class="author-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：<input type="text" name="author">
        </p>
        <p>
            <span class="press-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            出&nbsp;&nbsp;&nbsp;&nbsp;版&nbsp;&nbsp;&nbsp;社：<input type="text" name="press">
        </p>
        <input type="button" class="submit-btn" id="book-submit" value="提交">
    </form>
</div>
<footer>
    <a href="#">©2018-2019 二手书交易</a>
    <a href="#">意见反馈&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;隐私权声明&nbsp;&nbsp;&nbsp;使用条款</a>
</footer>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script>

    $("#book-submit").click(function (event){
        var bookForm = $("#bookForm").serializeArray();
        for (var i = 0;i<bookForm.length;i++){
            if (bookForm[i].value.length == 0){
                alert("请将信息填写完整！");
                return false;
            }
        }
        var status = "";
        $("#bookForm").ajaxSubmit({
            type:"POST",
            url:"upload.do",
            async:false,
            dataType:"json",
            success:function(data){
                status = data.msg;
            }
        });

        if (status == "1"){
            alert("上传成功");
        }else {
            alert("上传失败");
        }

    });

</script>
</html>
