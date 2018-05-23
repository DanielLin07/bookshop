<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>编辑书本信息</title>
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

    <form class="nav-search">
        <input type="search" class="searchIn" name="name" placeholder="搜图书...">
        <button class="search-logo"><img src="<%=request.getContextPath()%>/img/search2.png"></button>
    </form>

    <div class="nav-info">
        <a href="#" class="username">${user.getName()}</a>
        <a href="/myBookshelf.do" class="bookshelf">||&nbsp;&nbsp;&nbsp;我的书架</a>
        <a href="/users/logout.do" class="logout">[ 退 出 ]</a>
    </div> <!-- nav-info-end -->
</nav>

<div class="titleName">
    <h3>编辑二手书信息</h3>
</div>

<div class="container">
    <form enctype="multipart/form-data" id="bookForm">
        <input type="hidden" name="id" value="${book.getId()}" id="bookId">
        <p>
            <span class="pic-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            修改书图片： <input type="file" id="book-file" name="image">
            <img id="book-pic" src="<%=request.getContextPath()%>/img/book-list/article/${book.getBookImage().getId()}.jpg">
        </p>
        <p>
            <span class="name-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="name" value="${book.getName()}" id="bookName">
        </p>
        <p>
            <span class="author-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：<input type="text" name="author" value="${book.getAuthor()}" id="bookAuthor">
        </p>
        <p>
            <span class="name-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类：<select id="category" name="category.id">
            <c:forEach items="${categories}" var="category">
                <option value="${category.key}" id="bookCategory">${category.value}</option>
            </c:forEach>
        </select>
        </p>
        <p>
            <span class="origin-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;原&nbsp;价：<input type="text" name="originalPrice" value="${book.getOriginalPrice()}" id="bookOriginalPrice">
        </p>
        <p>
            <span class="price-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;售&nbsp;价：<input type="text" name="price" value="${book.getPrice()}" id="bookPrice">
        </p>
        <p>
            <span class="press-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            出&nbsp;&nbsp;&nbsp;&nbsp;版&nbsp;&nbsp;&nbsp;社：<input type="text" name="press" value="${book.getPress()}" id="bookPress">
        </p>
        <p>
            <span class="time-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            出&nbsp;版&nbsp;&nbsp;时&nbsp;间：<input type="month" name="publishDate" value="${book.getPublishDate()}" id="bookDate">
        </p>
        <p>
            <span class="version-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            印&nbsp;刷&nbsp;&nbsp;版&nbsp;本：<input type="text" name="version" value="${book.getVersion()}" id="bookVersion">
        </p>
        <p>
            <span class="condition-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;品&nbsp;相：<input type="text" name="degree" value="${book.getDegree()}" id="bookDegree">
        </p>
        <p>
            <span class="desc-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;描&nbsp;述：<input type="text" name="description" value="${book.getDescription()}" id="bookDescription">
        </p>

        <input type="button" class="submit-btn" id="book-submit" value="提交">
    </form>
</div>
<footer>
    <a href="#">©2018-2019 二手书交易</a>
    <a href="#">意见反馈&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;隐私权声明&nbsp;&nbsp;&nbsp;使用条款</a>
</footer>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script type="text/javascript">

    window.onload = function () {
        var id = $("#bookId").val();
        var id_ = {"id":id};
        var jsonData = JSON.stringify(id_);
        $.ajax({
            type:"POST",
            url:"/books/categories",
            async:false,
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            data:jsonData,
            success:function (result) {
                var opts = document.getElementById("category");
                var categoryId = result.data.categoryId;
                console.log(categoryId);
                if (categoryId != ""){
                    for(var i = 0;i<opts.options.length;i++){
                        if(categoryId == opts.options[i].value){
                            opts.options[i].selected = "selected";
                            break;
                        }
                    }
                }
            }
        });
    };

    $("#book-submit").click(function (event){
        $("#bookForm").ajaxSubmit({
            type:"POST",
            url:"/books/renewal",
            async:false,
            dataType:"json",
            success:function(result){
                if (result.resultCode == 200){
                    alert("修改成功");
                }else {
                    alert(result.message);
                }
            }
        });
    });

    $(function(){
        $("#book-file").change(function(){
            var filePath = $(this).val().split("\\");
            var len = filePath.length;
            var file = filePath[len-1];
            if (!file) {
                filePath = "img/loadPic.png"
            }
            filePath = "img/"+file;
            // console.log(filePath);
            $("#book-pic").attr("src",filePath);

        })
    });
</script>
</body>
</html>
