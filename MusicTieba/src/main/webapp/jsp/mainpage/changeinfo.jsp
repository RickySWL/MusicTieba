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
<title>修改信息</title>
<style type="text/css">
body {
	background-image: url("../img/all_bg.png");
}

#web_head {
	height: 200px;
	width: 100%;
	border: 1px solid black;
	background-image: url("../img/info_head.png");
	background-size:100%  100%;
}

#frame {
	margin: 0px 100px 0px 100px;
	border: 1px solid black;
	padding: 20px;
	height: 600px;
}

#user_photo_a {
	height: 400px;
	width: 500px;
	padding: 20px;
	margin: 50px 50px;
	border: 1px solid black;
	float: right;
	margin: 50px 50px;
	background-image: url("../img/photo_b_a.jpg");
	background-size:100%  100%;
}

#photo {
	width: 160px;
	height: 160px;
}

.photo_tip {
	font-size: 50px;
	margin: 0px 200px;
}

#photo {
	margin: 50px 170px;
}

.change_photo {
	margin: 10px 210px;
}

#user_word_a {
	height: 400px;
	width: 300px;
	padding: 50px 0px 0px 50px;
}

#user_word_a input {
	margin: 30px 0px 10px 20px;
}

#word_tip {
	margin: 20px 20px;
	font-size: 30px;
}
</style>
</head>
<%
	User u = (User) session.getAttribute("ui");
	String userName = "";
	String email = "";
	String photoUrl = "";
	if (u != null) {
		userName = u.getUserName();
		email = u.getEmail();
		photoUrl = u.getPhotoUrl();
	}
%>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
var pu="<%=photoUrl%>";
$(document).ready(function(){
});
function check(){
	var un=$("#userName").val();
	var em=$("#email").val();
	if(checkname(un) && checkemail(em)){
		saveinfo(un,em);
	}else{
		alert("昵称和邮箱不能为空,请注意邮箱格式！");
	}
}
function checkname(un){
	if(un.length!== 0){
		return true;
	}else{
		return false;
	}
}
function checkemail(em){
	if(em.length!== 0){
		if(em.indexOf('@') == -1 || em.indexOf('.') == -1){
			return false;
		}else{
			return true;
		}
	}else{
		return false;
	}
}

$(function(){
	 $("#cancelaccount").click(function(){
		 if(confirm("您真的要删除账户吗？")){
			 location.href = "<%=basePath%>mainpage/cancelaccount";
		   }else{
			 return false;
		 }
	  });
	});

function saveinfo(un,em){
	$.ajax({
		data :{'un':un,'em':em},
		type : "POST",
		dataType : 'json',
		url : "<%=basePath%>mainpage/changeinfo",
		error : function(data) {
			alert("出错了！！:" + data.msg);
		},
		success : function(data) {
			alert(data.msg);
			if(data.msg=="修改成功！"){
				window.location.href="<%=basePath%>mainpage/mainpage";
				}

			}
		});
	}
</script>
<body>
	<div id="web_head"></div>
	<div id="frame">
		<div id="user_photo_a">
			<span class="photo_tip">头像</span><img id="photo" alt="头像"
				src="<%=basePath%><%=photoUrl%>"> <input class="change_photo"
				type="button" value="修改头像"
				onclick="javascript:window.location.href='<%=basePath%>mainpage/changephoto';">
		</div>
		<div id="user_word_a">
			<span id="word_tip">请编辑您的信息:</span>
			<div id="user_name_a" class="ua">
				<span>昵称：</span><input type="text" id="userName" name="userName"
					maxlength=30 value="<%=userName%>">
			</div>
			<div id="user_email_a" class="ua">
				<span>邮箱：</span><input type="text" id="email" name="email"
					maxlength=30 value="<%=email%>">
			</div>
			<div id="action_a" class="ua">
				<input type="button" onclick="check();" value="保存">
			</div>
			<div id="change_pwd_a" class="ua">
				<input type="button" value="修改密码"
					onclick="javascript:window.location.href='<%=basePath%>mainpage/changepwd';">
			</div>
			<div id="cancel_account_a" class="ua">
				<input id="cancelaccount" type="button" value="注销账号">
			</div>
			<div id="back_a" class="ua">
				<input type="button" value="返回主页"
					onclick="javascript:window.location.href='<%=basePath%>mainpage';">
			</div>
		</div>
	</div>
</body>
</html>