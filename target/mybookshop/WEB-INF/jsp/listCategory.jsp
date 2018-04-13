<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>

<script type="text/javascript">
    /*将post method 改变为delete*/
    $(function () {
        $(".delete").click(function () {
            var href = $(this).attr("href");
            $("#formdelete").attr("action", href).submit();
            return false;
        })
    })
</script>
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>name</td>
        <td colspan="2">操作栏</td>
    </tr>
    <c:forEach items="${cs}" var="c" varStatus="st">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td><a href="category/${c.id}">编辑</a></td>
            <td><a class="delete" href="category/${c.id}">删除</a></td>
        </tr>
    </c:forEach>
</table>

<div>
    <a href="?start=0">首页</a>
    <a href="?start=${page.getStart()-page.getCount()}">上一页</a>
    <a href="?start=${page.getStart()+page.getCount()}">下一页</a>
    <a href="?start=${page.getEnd()}">末页</a>
</div>

<form id="formdelete" action="" method="POST">
    <input type="hidden" name="_method" value="DELETE">
</form>
