<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<style type="text/css">
body {
	background-image: url("../img/all_bg.png");
}

#Frame {
	margin: 50px auto;
	height: 400px;
	width: 400px;
}

#web_title {
	height: 200px;
	text-align: center;
	font-size: 150px;
	color: #f0f8ff;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px
		4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333,
		0px 8px 7px #001135
}

#login_action {
	margin: 100px 50px 50px 50px;
}

#user_button input {
	margin: 15px 25px;
	width: 80px;
}

#account {
	margin: 15px 10px;
}

#pwd {
	margin: 15px 10px;
}
</style>
</head>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
function check(){
	var an=$("#accountNumber").val();
	var pw=$("#password").val();
	if(an.length!== 0 && pw.length!== 0){
		login(an,pw);
	}else{
		alert("账号或密码不能为空");
	}
}

function login(an,pw) {
		$.ajax({
			data :{'an':an,'pw':pw},
			type : "POST",
			dataType : 'json',
			url : "<%=basePath%>mainpage/login",
			error : function(data) {
				alert("出错了！！:" + data.msg);
			},
			success : function(data) {
				alert(data.msg);
				if(data.msg=="登录成功！"){
					window.location.href="<%=basePath%>mainpage";
				}

			}
		});
	}
	
function goback(){
	window.location.href="<%=basePath%>mainpage";
	}
	
function goRegister(){
	window.location.href="<%=basePath%>mainpage/register";
}
</script>
<body>
	<div id="web_title">随意音乐论坛</div>
	<div id="Frame">
		<div id="login_action">
			<div id="account">
				账号：<input type="text" name="accountNumber" id="accountNumber">
			</div>
			<div id="pwd">
				密码：<input type="password" name="password" id="password">
			</div>

			<div id="user_button">
				<input type="button" onclick="check();" value="登录"> <input
					type="button" value="注册"
					onclick="goRegister();">
			</div>
		</div>
		<div id="back_main">
			<input type="button" value="返回主页" onclick="goback();">
		</div>
	</div>
</body>
</html>