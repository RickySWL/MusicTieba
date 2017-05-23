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
<title>修改头像</title>
<style type="text/css">
body {
	background-image: url("../img/all_bg.png");
}

#m_frame {
	border: 1px solid black;
	width: 500px;
	height: 400px;
	margin: 100px 0px 0px 400px;
	padding: 10px;
}

.up_a {
	width: 100px;
	margin: 60px 90px 0px 90px;
}

.tips {
	height: 20px;
	margin: 0px 100px 30px 100px;
}
.button_a{
	margin: 20px 90px 10px 90px;
	}
</style>
</head>
<body>
	<div id="m_frame">
		<form action="changephoto" method="post" enctype="multipart/form-data">
			<div class="up_a">
				<span>上传头像：</span> <input type="file" name="photoFile" />
			</div>
			<br> <span class="tips">(支持格式:jpg,jpeg,png,gif,bmp)</span> <br>
			<input class="button_a" type="submit" value="保存"> <input
				type="button" value="返回" class="button_a"
				onclick="javascript:window.location.href='<%=basePath%>mainpage/changeinfo';">
		</form>
	</div>
</body>
</html>