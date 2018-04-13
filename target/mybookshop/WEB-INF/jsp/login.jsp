<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>校园二手书交易平台</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
    <div id="login-container">
        <h2>校园二手书交易平台</h2>
        <br />
        <form>
            <input type="text" id="studentid" class="userName" placeholder="学号"><br/>
            <input type="password" id="password" class="password" placeholder="密码"><br/>
            <a class="forgetPass" href="">忘记密码?</a><br/>
            <button class="loginBtn" id="login-button" type="submit">登录</button><br/>
        </form>
        <p id="errorInfo"></p>
    </div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.js"></script>
<script>
    $('#login-button').click(function (event) {
        $('#errorInfo').html("");
        var studentid_ = $('#studentid').val();
        var password_  =$('#password').val();
        if(studentid_.length == 0 || password_.length == 0) {
            $('#errorInfo').html("请输入学号或密码！");
            return false;
        }
        var user_ = {"studentid":studentid_,"password":password_};
        var jsonData = JSON.stringify(user_);
        var flag = "";
        $.ajax({
            type:"POST",
            url:"checkLogin.do",
            async:false,
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            data:jsonData,
            success:function (msg) {
                flag = msg;
            }
        });
        if("0" == flag) {
            event.preventDefault();
            location.href = "home.do";
        }else {
            event.preventDefault();
            $('#errorInfo').html("学号或密码输入错误！");
        }
    })
</script>
</html>
