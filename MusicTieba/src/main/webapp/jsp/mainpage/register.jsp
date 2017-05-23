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
<title>注册</title>
<style type="text/css">
body {
	background-image: url("../img/all_bg.png");
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

#main_frame {
	border: 1px solid #708090;
	margin: 50px auto;
	height: 500px;
	width: 500px;
	margin: 50px auto;
}

.user_input {
	margin: 35px 20px;
}
#action_button input{
    width:70px;
	margin: 10px 20px;
}
</style>
<script type="text/javascript">
	function $(id) {
		return document.getElementById(id);
	}

	function check() {
		var email = $("email").value;
		var password = $("password").value;
		var repassword = $("repassword").value;
		var name = $("userName").value;
		var account = $("account").value;

		$("emailInfo").innerHTML = "";
		$("passwordInfo").innerHTML = "";
		$("repasswordInfo").innerHTML = "";
		$("nameInfo").innerHTML = "";
		$("accountInfo").innerHTML = "";

		if (email == "") {
			$("emailInfo").innerHTML = "Email值不能为空";
			return false;
		}

		if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
			$("emailInfo").innerHTML = "邮箱格式不正确,必须包含@和.";
			return false;
		}

		if (password == "") {
			$("passwordInfo").innerHTML = "密码不能为空";
			return false;
		}

		if (password.length < 6) {
			$("passwordInfo").innerHTML = "密码长度必须大于或者等于6";
			return false;
		}

		if (repassword != password) {
			$("repasswordInfo").innerHTML = "两次输入的密码不一致";
			return false;
		}

		if (name == "") {
			$("nameInfo").innerHTML = "昵称不能为空";
			return false;
		}

		if (account == "") {
			$("accountInfo").innerHTML = "账号不能为空";
			return false;
		}
		if (account.length < 6) {
			$("accountInfo").innerHTML = "账号长度必须大于或者等于6";
			return false;
		}
	}

	function checkEmail() { //校验Email  
		$('emailInfo').innerHTML = "";
		var email = $('email').value;
		if (email == "") {
			$('emailInfo').innerHTML = "Email值不能为空";
			return false;
		}

		if (email.indexOf('@') == -1 || email.indexOf('.') == -1) {
			$('emailInfo').innerHTML = "Email必须包含@和.";
			return false;
		}
	}

	function checkPassword() { //校验密码  
		$('passwordInfo').innerHTML = "";
		var password = $('password').value;
		if (password == "") {
			$("passwordInfo").innerHTML = "密码不能为空";
			return false;
		}

		if (password.length < 6) {
			$("passwordInfo").innerHTML = "密码长度必须大于或者等于6";
			return false;
		}
	}

	function checkRepassword() { //校验重新输入的密码  
		$('repassword').innerHTML = "";
		var repassword = $('repassword').value;
		if (repassword != password) {
			$("repasswordInfo").innerHTML = "两次输入的密码不一致";
			return false;
		}
	}
	function checkName() {//校验昵称 
		$('nameInfo').innerHTML = "";
		var name = $('userName').value;
		if (name == "") {
			$("nameInfo").innerHTML = "昵称不能为空";
			return false;
		}
	}

	function goback(){
		window.location.href="<%=basePath%>mainpage";
	}

	function checkaccount() { //校验账号
		$('accountInfo').innerHTML = "";
		var account = $('account').value;
		if (account == "") {
			$("accountInfo").innerHTML = "账号不能为空";
			return false;
		}
		if (account.length < 6) {
			$("accountInfo").innerHTML = "账号长度必须大于或者等于6";
			return false;
		}
	}
</script>
</head>
<body>
	<div id="web_title">随意音乐论坛</div>
	<div id="main_frame">
		<form action="register" method="post" name="register"
			enctype="multipart/form-data" onSubmit="return check()">
			<div class="user_input">
				输入昵称： <input type="text" name="userName" id="userName" maxlength=6
					onblur="checkName();" /><span id="nameInfo"></span>
			</div>
			<div class="user_input">
				创建账号： <input type="text" name="account" id="account" maxlength=10
					onblur="checkaccount();" /><span id="accountInfo"></span>
			</div>
			<div class="user_input">
				输入密码： <input type="password" name="password" id="password" maxlength=10
					onblur="checkPassword();" /><span id="passwordInfo"></span>
			</div>
			<div class="user_input">
				确认密码： <input type="password" name="repassword" id="repassword"
					onblur="checkRepassword();" /><span id="repasswordInfo"></span>
			</div>
			<div class="user_input">
				上传头像： <input type="file" name="photoFile" /> <span><br>(支持格式:jpg,jpeg,png,gif,bmp)</span>
			</div>
			<div class="user_input" >
				电子邮箱： <input type="text" name="email" id="email" maxlength=30
					onblur="checkEmail();" /><span id="emailInfo"></span>
			</div>
			<div class="user_input" id="action_button">
				<input type="submit" value="注册" /> <input type="reset" value="重置" /><input
					type="button" value="返回主页" onclick="goback();">
			</div>
		</form>
	</div>
</body>
</html>