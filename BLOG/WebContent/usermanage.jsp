<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.bean.User,com.db.*"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>博客管理系统</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">
<link rel="apple-touch-icon-precomposed" href="./images/icon/icon.png">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" href="./images/icon/favicon.ico">
<script src="./js/jquery-2.1.4.min.js"></script>
</head>

<body class="user-select">
	<section class="container-fluid">
		<header>
			<nav class="navbar navbar-default navbar-fixed-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand">Blogger</a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<li><a>管理员你好！</a></li>
							<li><a href="login.jsp"
								onClick="if(!confirm('是否确认退出？'))return false;">退出登录</a></li>
							<li><a data-toggle="modal" data-target="#WeChat">帮助</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<div class="row">
			<aside class="col-sm-3 col-md-2 col-lg-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="usermanage.jsp">用户管理</a></li>
					<li><a href="blogmanage.jsp">博客管理</a></li>
				</ul>
			</aside>
			<div
				class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main"
				id="main">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<tr>
							<th>序号</th>
							<th>用户名</th>
							<th>密码</th>
							<th>身份（y:用户，g:管理员）</th>
						</tr>

						<%
						    UOperation db = new UOperation();
						    String name = request.getParameter("name");
						    String sql = null;
						    if (name == null) {
								sql = "select * from user";
						    } else {
								sql = "select * from user where name like '%" + name + "%'";
						    }
						    List<User> list = db.getUser(sql);
						    request.setAttribute("lst", list);
						%>
						<c:forEach items="${lst}" var="user">
							<tr>
								<th>${user.id}</th>
								<th>${user.name}</th>
								<th>${user.password}</th>
								<th>${user.status}</th>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="table-responsive">
					<form action="user2" method="post">
						<li>请选择操作：</li>
						<input class="sex" type="text" th:field="*{sex}" list="listItem"
							name="action">
						<datalist id="listItem">
							       
							<option>add</option>
							       
							<option>delete</option>
							<option>update</option>
							<option>find</option>
						</datalist>
						<br />
						<br /> <li>账号名:</li><input type="text" name="name"
							value=<%=request.getAttribute("name")%>> <br />
						<br /> <li>密码:</li><input type="text" name="password"
							value=<%=request.getAttribute("password")%>><br />
						<br /><li> 管理员权限:</li><input class="sex1" type="text" th:field="*{sex1}" list="listItem1" name="status" value=<%=request.getAttribute("status")%>>
						<datalist id="listItem1">
						<option>g</option><option>p</option>
						</datalist>
						<br />
						<br /> <input class="btn btn-primary" type="submit" value="开始"
							name="OK">
					</form>
				</div>
			</div>
	</section>
	<!--微信二维码模态框-->
	<div class="modal fade user-select" id="WeChat" tabindex="-1"
		role="dialog" aria-labelledby="WeChatModalLabel">
		<div class="modal-dialog" role="document"
			style="margin-top: 120px; max-width: 280px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="WeChatModalLabel"
						style="cursor: default;">微信扫一扫</h4>
				</div>
				<div class="modal-body" style="text-align: center">
					<img src="./images/weixin.jpg" alt="" style="cursor: pointer" />
				</div>
			</div>
		</div>
	</div>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/admin-scripts.js"></script>
</body>
</html>
