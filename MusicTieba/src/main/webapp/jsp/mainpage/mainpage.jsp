<%@ page language="java"
	import="com.java.util.*,com.musictieba.entity.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>随意O(∩_∩)O~~音乐论坛</title>
</head>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<style>
body {
	background-color: #DDDDDD;
}

.user_photo img {
	width: 80px;
	height: 80px;
}

#login {
	float: right;
	width: 30%;
	height: 200px;
	text-align: center;
	background-image: url("./img/login_user.jpeg");
	background-size: cover;
}

#login input {
	width: 80px;
	margin: 70px 40px;
}

#userinfo {
	float: right;
	width: 30%;
	height: 200px;
	text-align: center;
	background-image: url("./img/after_login.jpg");
	background-size: 100% 100%;
}

#photo {
	padding: 20px 0px;
}

#user_name {
	padding: 10px 0px;
}

#main_head_img {
	height: 200px;
	width: 70%;
	background-image: url("./img/head_img.jpg");
	background-size: 100% 100%;
	text-align: center;
	font-size:120px;
	color: transparent;
	-webkit-text-stroke: 2px pink;
	letter-spacing: 0.04em;
	background-color:
}

#tieba_side {
	width: 50%;
	float: left;
	height: 700px;
	background-image: url("./img/tieba_side.jpg");
	background-size: cover;
}

#music_side {
	float: right;
	width: 50%;
	height: 700px;
	background-image: url("./img/music_side.jpg");
	background-size: cover;
}

#listen_music {
	display: none;
}

#gomusic {
	width: 200px;
	height: 50px;
	font-size: 20px;
	background: url("./img/bg39.jpg") repeat-x left top;
	background-size: 100% 100%;
	color: #FFF;
	padding: 0px 20px 0px 20px;
	margin: 300px 250px;
}

#gotieba {
	width: 200px;
	height: 50px;
	font-size: 20px;
	background: url("./img/bg40.jpg") no-repeat left top;
	background-size: 100% 100%;
	border: 0;
	color: #FFF;
	float: right;
	margin: 300px 250px;
}
</style>
<script type="text/javascript" charset="UTF-8">
var un='${sessionScope.ui.userName}';
var pu='${sessionScope.ui.photoUrl}';
var ut='${sessionScope.ui.admin}'==='true';

	$(document).ready(function() {
		if (un.length == 0) {
			$("#login").show();
			$("#userinfo").hide();
		} else {
			$("#login").hide();
			$("#userinfo").show();
			if (ut) {
				$("#userlist").show();
			} else {
				$("#userlist").hide();
			}
		}
		bgMusic();
	});
	function bgMusic(){
		var prefix="<%=basePath%>musicfile/main_music";
		var suffix=".mp3"
		var r=Math.round(Math.random()*3);
		var link=prefix+r+suffix;
		$("#bgm").attr("src",link);
	}
</script>
<body>
	<div id="login">
		<input type="button" value="登录"
			onclick="javascript:window.location.href='<%=basePath%>mainpage/login';">
		<input type="button" value="注册"
			onclick="javascript:window.location.href='<%=basePath%>mainpage/register';">
	</div>
	<div id="userinfo">
		<div class="user_photo">
			<img id="photo" alt="头像" src="<%=basePath%>${sessionScope.ui.photoUrl}">
		</div>
		<div id="user_name">
			<b>会员: ${sessionScope.ui.userName},欢迎登录!
			</b>
		</div>
		<div class="user_action">
			<input id="userlist" type="button" value="用户列表"
				onclick="javascript:window.location.href='<%=basePath%>mainpage/userlist';">
			<input type="button" value="修改资料"
				onclick="javascript:window.location.href='<%=basePath%>mainpage/changeinfo';">
			<input type="button" value="退出登陆"
				onclick="javascript:window.location.href='<%=basePath%>mainpage/logout';">

		</div>
	</div>
	<div id="main_head_img">随意音乐论坛</div>
	<div id="tieba_side">
		<input type="button" value="进入论坛" id="gotieba"
			onclick="javascript:window.location.href='<%=basePath%>note/listandadd';">
	</div>
	<div id="music_side">
		<input type="button" value="歌曲资源" id="gomusic"
			onclick="javascript:window.location.href='<%=basePath%>music/searchmusic';">
	</div>
	<div id="listen_music">
		<audio id="bgm" src="" autoplay="autoplay">
		您的浏览器不支持audio标签！
		</audio>
	</div>
</body>
</html>