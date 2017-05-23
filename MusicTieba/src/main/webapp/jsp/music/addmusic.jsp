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
<title>上传音乐</title>
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

#addmusic{
	border: 2px solid #708090;
	margin: 50px auto;
	height: 500px;
	width: 700px;
	margin: 50px auto;
}
.u_input{
	margin: 20px 190px;
}
</style>
</head>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$("#sub").click(function() {
			var mf = $("#musicFile").val();
			var mn = $("#musicName").val();
			if (mf.length == 0 || mn.lengt == 0) {
				alert("上传歌曲或歌曲名不能为空！")
			} else {
				$("#addmusicform").submit();
			}
		});
	});
</script>
<body>
	<div id="web_title">随意音乐论坛</div>
	<div id="addmusic">
		<form id="addmusicform" action="addmusic" method="post"
			enctype="multipart/form-data">
			<div class="u_input">
				歌曲名：<input type="text" id="musicName" name="musicName" maxlength=30>
			</div>
			<div class="u_input">
				艺术家：<input type="text" id="singer" name="singer" maxlength=30>(可不填)
			</div>
			<div class="u_input">
				专辑：<input type="text" id="album" name="album" maxlength=30>(可不填)
			</div>
			<div class="u_input">
				发行时间：<input type="text" id="year" name="year" maxlength=30>(可不填)
			</div>
			<div class="u_input">
				上传歌曲： <input type="file" id="musicFile" name="musicFile" /><br>
				<span>(支持大小:10M 支持格式:mp3,wav,wma)</span>
			</div>
			<div class="u_input">
				上传封面： <input type="file" name="coverFile" /><br>
				<span>(支持格式:jpg,jpeg,png,gif,bmp)(可不选)</span>
			</div>
			<div class="u_input">
				<input type="button" id="sub" value="上传"> <input
					type="button" value="返回"
					onclick="javascript:window.location.href='<%=basePath%>music/searchmusic';">
			</div>
		</form>

	</div>
</body>
</html>