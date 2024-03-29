<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.action.UserServlet1,com.db.*"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>博客管理系统(登陆)</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/login.css">
<link rel="apple-touch-icon-precomposed" href="./images/icon/icon.png">
<link rel="shortcut icon" href="./tt/./images/icon/favicon.ico">
<script src="./js/jquery-2.1.4.min.js"></script>
</head>

<body class="user-select">
<div class="container">
  <div class="siteIcon"><img src="./images/icon/icon.png" alt="" data-toggle="tooltip" data-placement="top" title="欢迎使用博客管理系统" draggable="false" /></div>
  <form action="user1" method="post" autocomplete="off" class="form-signin">
    <h2 class="form-signin-heading">欢迎使用Blog系统</h2>
    <label for="userName" class="sr-only">用户名</label>
    <input type="text" id="name" name="name" class="form-control" placeholder="请输入用户名" required autofocus autocomplete="off" maxlength="10">
    <label for="userPwd" class="sr-only">密码</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码" required autocomplete="off" maxlength="18">
    <a><button class="btn btn-lg btn-primary btn-block" type="submit" id="signinSubmit">用户登录</button></a>
    </form>
    <form autocomplete="off"  class="form-signin">
    <a href="tourist.jsp"><button class="btn btn-lg btn-primary btn-block" type="button" id="signinSubmit">游客登录</button></a>
    </form>
</div>
<script src="js/bootstrap.min.js"></script> 
<script>
$('[data-toggle="tooltip"]').tooltip();
window.oncontextmenu = function(){
	//return false;
};
$('.siteIcon img').click(function(){
	window.location.reload();
});
$('#signinSubmit').click(function(){
	if($('#name').val() === ''){
		$(this).text('用户名不能为空');
	}else if($('#password').val() === ''){
		$(this).text('密码不能为空');
	}else{
		$(this).text('请稍后...');
	}
});
</script>
</body>
</html>
