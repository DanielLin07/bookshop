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
<div class="titleName">
    <h3>上传二手书</h3>
    <p>设置二手书信息</p>
</div>

<div class="container">
    <form enctype="multipart/form-data" id="bookForm" class="upload-form">
        <p>
            <span class="pic-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            上传书图片： <input type="file" accept="image/*" name="image">
        </p>
        <p>
            <span class="name-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="name">
        </p>
        <p>
            <span class="author-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：<input type="text" name="author">
        </p>
        <p>
            <span class="name-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类：<select id="category" name="category.id">
            <c:forEach items="${categories}" var="category">
                <option value="${category.key}">${category.value}</option>
            </c:forEach>
        </select>
        </p>
        <input type="hidden" name="bookType" value="1">
        <p>
            <span class="origin-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;原&nbsp;价：<input type="text" name="originalPrice">
        </p>
        <p>
            <span class="price-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;售&nbsp;价：<input type="text" name="price">
        </p>
        <p>
            <span class="press-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            出&nbsp;&nbsp;&nbsp;&nbsp;版&nbsp;&nbsp;&nbsp;社：<input type="text" name="press">
        </p>
        <p>
            <span class="time-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            出&nbsp;版&nbsp;&nbsp;时&nbsp;间：<input type="month" name="publishDate">
        </p>
        <p>
            <span class="version-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            印&nbsp;刷&nbsp;&nbsp;版&nbsp;本：<input type="text" name="version">
        </p>
        <p>
            <span class="condition-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;品&nbsp;相：<input type="text" name="degree">
        </p>
        <p>
            <span class="desc-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;描&nbsp;述：<input type="text" name="description">
        </p>

        <input type="button" class="submit-btn" id="book-submit" value="提交">
    </form>
</div>

<dialog id="modal-dialog">
    <div id="modal-text"></div>
    <img class="modal-close-icon" src="<%=request.getContextPath()%>/img/close.png">
    <div  class="modal-close">
        <img src="<%=request.getContextPath()%>/img/success.png">
    </div>
</dialog>

<footer>
    <a href="#">©2018-2019 二手书交易</a>
    <a href="#">意见反馈&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;隐私权声明&nbsp;&nbsp;&nbsp;使用条款</a>
</footer>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.js"></script>
<script src="<%=request.getContextPath()%>/js/upload.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/session.js"></script>
<script>

    $("#book-submit").click(function (event){
        var bookForm = $("#bookForm").serializeArray();
        for (var i = 0;i<bookForm.length;i++){
            if (bookForm[i].value.length == 0){
                alert("请将信息填写完整！");
                return false;
            }
        }

        $("#bookForm").ajaxSubmit({
            type:"POST",
            url:"/books/",
            async:false,
            dataType:"json",
            success:function(result){
                if (result.resultCode == 200){
                    alert("上传成功");
                }else {
                    alert(result.message);
                }
            }
        });

        /*// 显示模态窗口
        var modal = document.getElementById("modal-dialog");
        if (status == "1"){
            $("#modal-text").html("上传成功");
        }else {
            $("#modal-text").html("上传失败");
        }
        modal.showModal();*/
    });

</script>
</html>
