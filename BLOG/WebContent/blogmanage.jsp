<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.bean.Blog,com.db.*"%>
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
          <a class="navbar-brand" >Blogger</a> </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
          <li><a>管理员你好！</a></li>
            <li><a href="login.jsp" onClick="if(!confirm('是否确认退出？'))return false;">退出登录</a></li>
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
							<th>标题</th>
							<th>内容</th>
							<th>图片</th>
							<th>发布时间</th>
						</tr>

						<%
						    BOperation db = new BOperation();
						    String name = request.getParameter("name");
						    String sql = null;
						    if (name == null) {
								sql = "select * from blog";
						    } else {
								sql = "select * from blog where name like '%" + name + "%'";
						    }
						    List<Blog> list = db.getBlog(sql);
						    request.setAttribute("lst", list);
						%>
						<c:forEach items="${lst}" var="blog">
							<tr>
								<th>${blog.id}</th>
								<th>${blog.name}</th>
								<th>${blog.title}</th>
								<th>${blog.content}</th>
								<th><img src="${blog.content}"></th>
								<th>${blog.time}</th>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="table-responsive">
				操作页面
				</div>
			</div>
	
  </div>
</section>
<!--微信二维码模态框-->
<div class="modal fade user-select" id="WeChat" tabindex="-1" role="dialog" aria-labelledby="WeChatModalLabel">
  <div class="modal-dialog" role="document" style="margin-top:120px;max-width:280px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="WeChatModalLabel" style="cursor:default;">微信扫一扫</h4>
      </div>
      <div class="modal-body" style="text-align:center"> <img src="./images/weixin.jpg" alt="" style="cursor:pointer"/> </div>
    </div>
  </div>
</div>
<script src="./js/bootstrap.min.js"></script> 
<script src="./js/admin-scripts.js"></script>
</body>
</html>