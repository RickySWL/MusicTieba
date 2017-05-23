<%@ page language="java"
	import="com.java.util.*,com.musictieba.entity.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>听歌</title>

<style type="text/css">
body {
	background-image: url("../img/all_bg.png");
}

#web_head {
	width: 100%;
	height: 200px;
}

#head_title {
	float: left;
	height: 200px;
	font-size: 150px;
	color: #f0f8ff;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px
		4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333,
		0px 8px 7px #001135;
	height: 200px;
}

#user_a {
	float: right;
	width: 25%;
	height: 200px;
	text-align: center;
	background-image: url("../img/after_login.jpg");
	background-size: 100% 100%;
}

#photo {
	width: 80px;
	height: 80px;
	margin: 30px 20px 10px 20px;
}

#login_tips {
	text-align: center;
	margin: 50px;
}

#button_a {
	margin: 30px 85px 10px 85px;
}

#listenmusic {
	margin: 10px 500px;
}

#listen_mp3 {
	border: 2px solid red;
}

table td {
	border: 2px solid #ccc;
}

.noteid {
	border: 2px solid #ccc;
}

#note_info {
	float: right;
	width: 29%;
}

.notetitle {
	text-align: center;
	font-size: 40px;
	margin: 20px 0px;
}

#note_button {
	float: right;
}

.notecontent {
	border: 2px solid #ccc;
	float: left;
	width: 70%;
	height: 500px;
}

.noteusername {
	border: 2px solid #ccc;
	margin: 10px 5px;
}

.notecreatetime {
	border: 2px solid #ccc;
	margin: 10px 5px;
}

.notelasttime {
	border: 2px solid #ccc;
	margin: 10px 5px;
}

.notefloors {
	border: 2px solid #ccc;
	margin: 10px 5px;
}

#note4music {
	height: 510px;
}

.commentcontent {
	width: 600px;
	height: 200px
}
</style>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
	var un = '${sessionScope.ui.userName}';
	var pu = '${sessionScope.ui.photoUrl}';
	var ut = '${sessionScope.ui.admin}' === 'true';
	var count = '${PageBean.count}';
	var pageno = ${PageBean.pageLength};
	var nowpageno = 1;

	$(document).ready(
			function() {
				isMenber();
				if (count == 0) {
					$("#comments").hide();
					$("#listinfo").hide();
					$("#jumpaction").hide();
					$("#tip").show();
				} else {
					$("#comments").show();
					$("#listinfo").show();
					$("#jumpaction").show();
					$("#tip").hide();
					$("#listinfo").html(
							'<span>共' + pageno + '页，' + count + '条记录，当前第'
									+ nowpageno + '页</span>');
				}
			});

	function isMenber() {
		if (un.length == 0) {
			$("#listenmusic").hide();
			$("#userinfo").hide();
			$("#login").show();
		} else {
			$("#listenmusic").show();
			$("#userinfo").show();
			$("#login").hide();
		}
	}
</script>
</head>
<body>
	<div id="web_head">
		<div id="head_title">随意音乐论坛</div>
		<div id="user_a">
			<div id="userinfo">
				<div>
					<img id="photo" alt="头像"
						src="<%=basePath%>${sessionScope.ui.photoUrl}">
				</div>
				<span><b>会员:${sessionScope.ui.userName},您好！ </b></span>
			</div>
			<div id="button_a">
				<input type="button" value="返回列表"
					onclick="javascript:window.location.href='<%=basePath%>music/searchmusic';">
				<input type="button" value="返回主页"
					onclick="javascript:window.location.href='<%=basePath%>mainpage';">
			</div>
			<div id="login">
				<span><b>只有登录了才能听歌哦！</b></span> <input type="button" value="登录"
					onclick="javascript:window.location.href='<%=basePath%>mainpage/login';">
				<input type="button" value="注册"
					onclick="javascript:window.location.href='<%=basePath%>mainpage/register';">
			</div>
		</div>
	</div>



	<div class="notetitle">
		<span>${note.title}</span>
		<div id="note_button">
			<input type="button" value="主题传送门"
				onclick="javascript:window.location.href='<%=basePath%>note/readandadd?noteid=${note.noteId}&topageno=1';">
		</div>
	</div>
	<div id="listenmusic">
		<audio id="listen_mp3" src="<%=basePath%>${musicUrl}" controls="controls"
			preload="auto"> </video>
	</div>

	<div id="note4music">

		<div class="notecontent">${note.content}</div>
		<div id="note_info">
			<div class="noteusername">楼主：${note.user.userName}</div>
			<div class="notecreatetime">发帖时间：<fmt:formatDate value="${note.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
            <div class="notelasttime">最后回复时间：<fmt:formatDate value="${note.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			<div class="notefloors">楼层： ${note.floors}</div>
		</div>
	</div>

	<div id="comments">
		<from>
		<table>
			<tr>
				<td>楼层</td>
				<td>内容</td>
				<td>回帖人</td>
				<td>回帖时间</td>
			</tr>
			<c:forEach items="${PageBean.source}" var="comment" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td><div class="commentcontent">${comment.content}</div></td>
					<td>${comment.user.userName}</td>
					<td><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</table>
		</from>
	</div>
	<div id="listinfo"></div>
	<div id="tip">一条讨论都没有，快去留下你的印记吧！</div>
</body>
</html>