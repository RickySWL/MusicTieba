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
<title>修改密码</title>
<style type="text/css">
body {
	background-image: url("../img/all_bg.png");
}

#m_frame {
	border: 1px solid black;
	width: 500px;
	height: 400px;
	margin: 100px 0px 0px 400px;
	padding: 20px;
}

#tips {
	font-size: 30px;
	margin: 20px 130px 20px 130px;
}

.input1 {
	margin: 50px 120px 20px 120px;
}

.input2 {
	margin: 50px 120px 20px 120px;
}

.input3 {
	margin: 50px 100px 20px 100px;
}
.button4a input{
	margin: 20px 0px 20px 140px;
}
</style>
</head>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	var oldpwd = "";
	var newpwd = "";
	var renewpwd = "";
	
	function check() {
		oldpwd = $("#oldpwd").val();
		newpwd = $("#newpwd").val();
		renewpwd = $("#renewpwd").val();
		
		if(newpwd.length<6 ){
			alert("密码长度不能小于6位！");
		}else{
			if (newpwd == renewpwd) {
				changepwd(oldpwd,newpwd)
			} else {
				alert("两次输入的新密码不一致");
			}
		}
	}
	
function changepwd(oldpwd,newpwd){
		$.ajax({
			data :{'oldpwd':oldpwd,'newpwd':newpwd},
			type : "POST",
			dataType : 'json',
			url : "<%=basePath%>mainpage/changepwd",
			error : function(data) {
				alert("出错了！" + data.msg);
				window.location.href="<%=basePath%>mainpage/changeinfo";
			},
			success : function(data) {
				alert(data.msg);
				if(data.msg=="修改密码成功！请重新登录"){
					window.location.href="<%=basePath%>mainpage/login";
				}
			}
		});
	}
</script>
<body>
	<div id="m_frame">
		<div id="tips">
			<b>请更改您的密码！</b>
		</div>
		<div class="input1">
			旧密码：<input type="password" id="oldpwd" name="oldpwd" mix="6">
		</div>
		<div class="input2">
			新密码：<input type="password" id="newpwd" name="newpwd">
		</div>
		<div class="input3">
			确认新密码：<input type="password" id="renewpwd" name="renewpwd">
		</div>
		<div class="button4a">
			<input type="button" onclick="check();" value="保存"><input
				type="button" value="返回"
				onclick="javascript:window.location.href='<%=basePath%>mainpage/changeinfo';">
		</div>
	</div>
</body>
</html>